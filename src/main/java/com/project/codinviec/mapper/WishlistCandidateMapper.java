package com.project.codinviec.mapper;

import com.project.codinviec.dto.BasicUserDTO;
import com.project.codinviec.dto.WishlistCandidateDTO;
import com.project.codinviec.entity.WishlistCandidate;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.key.WishlistCandidateKey;
import com.project.codinviec.request.WishlistCandidateRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WishlistCandidateMapper {

        public List<WishlistCandidateDTO> mappedToWishlistCandidateDTO(List<WishlistCandidate> wishlistCandidates) {
                // Map và group by duy nhất id hr
                List<WishlistCandidateDTO> result = new ArrayList<>();
                Map<String, List<User>> groupByHrId = wishlistCandidates.stream()
                                .collect(Collectors.groupingBy(
                                                ws -> ws.getUserHr().getId(),
                                                Collectors.mapping(WishlistCandidate::getUserCandidate,
                                                                Collectors.toList())));

                // Cách for map phải dùng entry (entry bao gồm key và value)
                for (Map.Entry<String, List<User>> entry : groupByHrId.entrySet()) {
                        // Lấy thông tin userHr
                        User userHr = wishlistCandidates.stream()
                                        .map(WishlistCandidate::getUserHr)
                                        .filter(u -> u.getId().equalsIgnoreCase(entry.getKey()))
                                        .findFirst()
                                        .orElse(null);

                        if (userHr == null)
                                continue;

                        // Chuyển candidate user trong entry thành BasicUserDTO
                        List<BasicUserDTO> userCandidate = entry.getValue().stream()
                                        .map(u -> BasicUserDTO.builder()
                                                        .id(u.getId())
                                                        .firstName(u.getFirstName())
                                                        .lastName(u.getLastName())
                                                        .avatar(u.getAvatar())
                                                        .build())
                                        .toList();

                        // Dùng Builder pattern thay vì setter
                        WishlistCandidateDTO wishlistCandidateDTO = WishlistCandidateDTO.builder()
                                        .id(userHr.getId())
                                        .firstName(userHr.getFirstName())
                                        .lastName(userHr.getLastName())
                                        .avatar(userHr.getAvatar())
                                        .wistlistCandidates(userCandidate)
                                        .build();
                        result.add(wishlistCandidateDTO);
                }
                return result;
        }

        public WishlistCandidate saveWishlistCandidate(User userHr, User userCandidate,
                        WishlistCandidateRequest request) {
                if (request == null)
                        return null;
                return WishlistCandidate.builder()
                                .wishlistCandidateKey(
                                                WishlistCandidateKey.builder()
                                                                .hrId(request.getHrId())
                                                                .candidateId(request.getCandidateId())
                                                                .build())
                                .userHr(userHr)
                                .userCandidate(userCandidate)
                                .build();
        }
}
