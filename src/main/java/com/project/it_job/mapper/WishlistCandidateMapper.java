package com.project.it_job.mapper;

import com.project.it_job.KeyEntity.WishlistCandidateKey;
import com.project.it_job.dto.BasicUserDTO;
import com.project.it_job.dto.WishlistCandidateDTO;
import com.project.it_job.entity.WishlistCandidate;
import com.project.it_job.entity.auth.User;
import com.project.it_job.request.WishlistCandidateRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WishlistCandidateMapper {
    public List<WishlistCandidateDTO> wishlistCandidateToWishlistCandidateDTO(List<WishlistCandidate> wishlistCandidates) {
//        Map và group by duy nhất id hr
        List<WishlistCandidateDTO> result = new ArrayList<>();
        Map<String, List<User>> groupByHrId = wishlistCandidates.stream()
                .collect(Collectors.
                        groupingBy(ws-> ws.getUserHr().getId(),
                                Collectors.mapping(w->w.getUserCandidate(),Collectors.toList())
                        ));

//        cách for map phải dùng entry (entry bao gồm key và value)
        for (Map.Entry<String, List<User>> entry : groupByHrId.entrySet()) {
//            Lấy thông tin userHr
            User userHr = wishlistCandidates.stream()
                    .map(ws->ws.getUserHr())
                    .filter( u -> u.getId().equalsIgnoreCase(entry.getKey()))
                    .findFirst().orElse(null);


//            Chuyển candidate user trong entry thành BasicUserDTO
            List<BasicUserDTO> userCadidate = entry.getValue().stream().map(u->{
                return BasicUserDTO.builder()
                        .id(u.getId())
                        .firstName(u.getFirstName())
                        .lastName(u.getLastName())
                        .avatar(u.getAvatar())
                        .build();
            }).toList();

            WishlistCandidateDTO wishlistCandidateDTO = new WishlistCandidateDTO();
            wishlistCandidateDTO.setId(userHr.getId());
            wishlistCandidateDTO.setFirstName(userHr.getFirstName());
            wishlistCandidateDTO.setLastName(userHr.getLastName());
            wishlistCandidateDTO.setAvatar(userHr.getAvatar());
            wishlistCandidateDTO.setWistlistCandidates(userCadidate);
            result.add(wishlistCandidateDTO);
        }
        return result;
    }
    public WishlistCandidate MappedWishlistCandidate(User userHr, User userCandidate,WishlistCandidateRequest request){
        return WishlistCandidate.builder()
                .wishlistCandidateKey(
                        WishlistCandidateKey.builder()
                                .hrId(request.getHrId())
                                .candidateId(request.getCandidateId())
                                .build()
                )
                .userHr(userHr)
                .userCandidate(userCandidate)
                .build();
    }
}
