package com.project.codinviec.service.imp;


import com.project.codinviec.dto.WishlistCandidateDTO;
import com.project.codinviec.entity.WishlistCandidate;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.key.WishlistCandidateKey;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.exception.common.ParamExceptionHandler;
import com.project.codinviec.mapper.WishlistCandidateMapper;
import com.project.codinviec.repository.WishlistCandidateRepository;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.WishlistCandidateRequest;
import com.project.codinviec.service.WishlistCandidateService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistCandidateServiceImp implements WishlistCandidateService {
    private final WishlistCandidateRepository wishlistCandidateRepository;
    private final WishlistCandidateMapper wishlistCandidateMapper;
    private final UserRepository userRepository;

    @Override
    public List<WishlistCandidateDTO> getAllWishlistCandidates() {
        return wishlistCandidateMapper.mappedToWishlistCandidateDTO(wishlistCandidateRepository.findAll());
    }

    @Override
    public Page<WishlistCandidateDTO> getAllWishlistCandidatesPage(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageSize() == 0) {
            throw new ParamExceptionHandler("page size truyền lên không hợp lệ");
        }
        Pageable pageable = PageRequest.of(pageRequestCustom.getPageNumber() - 1, pageRequestCustom.getPageSize());
        Page<WishlistCandidate> wishlistCandidatePage = wishlistCandidateRepository.findAll(pageable);
        List<WishlistCandidateDTO> wishlistCandidates = wishlistCandidateMapper.mappedToWishlistCandidateDTO(wishlistCandidatePage.getContent());
        return new PageImpl<>(wishlistCandidates, pageable, wishlistCandidatePage.getTotalElements());
    }

    @Override
    public List<WishlistCandidateDTO> getWishlistCandidateByHrId(String hrId) {
        userRepository.findById(hrId)
                .orElseThrow(() -> new ParamExceptionHandler("Không tìm thấy hr id"));
        return wishlistCandidateMapper.mappedToWishlistCandidateDTO(wishlistCandidateRepository.findByUserHr_Id(hrId));
    }

    @Override
    @Transactional
    public List<WishlistCandidateDTO> saveWishlistCandidate(WishlistCandidateRequest wishlistCandidateRequest) {
        if (wishlistCandidateRequest.getHrId().equalsIgnoreCase(wishlistCandidateRequest.getCandidateId())) {
            throw new ParamExceptionHandler("Tham số truyền vào cùng 1 người không thể wishlist");
        }
        User userHr =  userRepository.findById(wishlistCandidateRequest.getHrId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id Hr"));

        User userCandidate = userRepository.findById(wishlistCandidateRequest.getCandidateId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id candidate"));

        WishlistCandidate wc = wishlistCandidateMapper.saveWishlistCandidate(userHr, userCandidate, wishlistCandidateRequest);
        wishlistCandidateRepository.save(wc);
        return wishlistCandidateMapper.mappedToWishlistCandidateDTO(wishlistCandidateRepository.findByUserHr_Id(userHr.getId()));
    }

    @Override
    @Transactional
    public List<WishlistCandidateDTO> deleteWistListCandidate(WishlistCandidateRequest wishlistCandidateRequest) {
        WishlistCandidateKey wishlistCandidateKey = new WishlistCandidateKey(wishlistCandidateRequest.getHrId(),wishlistCandidateRequest.getCandidateId());
        WishlistCandidate wishlistCandidate =  wishlistCandidateRepository.findById(wishlistCandidateKey)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy wishlist candidate id"));
        wishlistCandidateRepository.delete(wishlistCandidate);
        // Trả ra tk hr không còn user wishlist nữa hoặc mảng rỗng
        List<WishlistCandidate> getDeleteWc = wishlistCandidateRepository.findByUserHr_Id(wishlistCandidateKey.getHrId());
        return wishlistCandidateMapper.mappedToWishlistCandidateDTO(getDeleteWc);
    }
}
