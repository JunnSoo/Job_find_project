package com.project.it_job.service.imp;

import com.project.it_job.KeyEntity.WishlistCandidateKey;
import com.project.it_job.dto.WishlistCandidateDTO;
import com.project.it_job.entity.WishlistCandidate;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.exception.ParamExceptionHandler;
import com.project.it_job.mapper.WishlistCandidateMapper;
import com.project.it_job.repository.WishlistCandidateRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.WishlistCandidateRequest;
import com.project.it_job.service.WishlistCandidateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
        return wishlistCandidateMapper.wishlistCandidateToWishlistCandidateDTO(wishlistCandidateRepository.findAll());
    }

    @Override
    public List<WishlistCandidateDTO> getWishlistCandidateByHrId(String hrId) {
        return wishlistCandidateMapper.wishlistCandidateToWishlistCandidateDTO(wishlistCandidateRepository.findByUserHr_Id(hrId));
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

        WishlistCandidate wc = wishlistCandidateMapper.MappedWishlistCandidate(userHr, userCandidate,  wishlistCandidateRequest );
        wishlistCandidateRepository.save(wc);
//        Lấy id hr chủ yếu
        List<WishlistCandidate> getSavedWc = wishlistCandidateRepository.findByUserHr_Id(userHr.getId());

        return wishlistCandidateMapper.wishlistCandidateToWishlistCandidateDTO(getSavedWc);
    }

    @Override
    public List<WishlistCandidateDTO> deleteWistListCandidate(WishlistCandidateRequest wishlistCandidateRequest) {
        WishlistCandidateKey wishlistCandidateKey = new WishlistCandidateKey(wishlistCandidateRequest.getHrId(),wishlistCandidateRequest.getCandidateId());
        WishlistCandidate wishlistCandidate =  wishlistCandidateRepository.findById(wishlistCandidateKey)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy wishlist candidate id"));
        wishlistCandidateRepository.delete(wishlistCandidate);
//        trả ra tk hr không còn user wishlist nữa hoặc mảng rỗng
        List<WishlistCandidate> getDeleteWc = wishlistCandidateRepository.findByUserHr_Id(wishlistCandidateKey.getHrId());
        return wishlistCandidateMapper.wishlistCandidateToWishlistCandidateDTO(getDeleteWc);
    }
}
