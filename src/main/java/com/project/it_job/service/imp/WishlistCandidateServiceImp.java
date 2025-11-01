package com.project.it_job.service.imp;

import com.project.it_job.keyentity.WishlistCandidateKey;
import com.project.it_job.dto.WishlistCandidateDTO;
import com.project.it_job.entity.WishlistCandidate;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.exception.ParamExceptionHandler;
import com.project.it_job.mapper.WishlistCandidateMapper;
import com.project.it_job.repository.WishlistCandidateRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.WishlistCandidateRequest;
import com.project.it_job.service.WishlistCandidateService;
import jakarta.transaction.Transactional;
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
        List<WishlistCandidateDTO> wishlistCandidates =  wishlistCandidateMapper.mappedToWishlistCandidateDTO(wishlistCandidateRepository.findAll());

        if (pageRequestCustom.getPageSize() == 0) {
            throw new ParamExceptionHandler("page size truyền lên không hợp lệ");
        }
        Pageable pageable = PageRequest.of(pageRequestCustom.getPageNumber(),pageRequestCustom.getPageSize());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), wishlistCandidates.size());

//        cắt list
        List<WishlistCandidateDTO> pageContent = wishlistCandidates.subList(start, end);
//       tạo page
        return new PageImpl<>(pageContent, pageable, wishlistCandidates.size());
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

        WishlistCandidate wc = wishlistCandidateMapper.MappedWishlistCandidate(userHr, userCandidate,  wishlistCandidateRequest );
        wishlistCandidateRepository.save(wc);
        return wishlistCandidateMapper.mappedToWishlistCandidateDTO(wishlistCandidateRepository.findByUserHr_Id(userHr.getId()));
    }

    @Override
    public List<WishlistCandidateDTO> deleteWistListCandidate(WishlistCandidateRequest wishlistCandidateRequest) {
        WishlistCandidateKey wishlistCandidateKey = new WishlistCandidateKey(wishlistCandidateRequest.getHrId(),wishlistCandidateRequest.getCandidateId());
        WishlistCandidate wishlistCandidate =  wishlistCandidateRepository.findById(wishlistCandidateKey)
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy wishlist candidate id"));
        wishlistCandidateRepository.delete(wishlistCandidate);
//        trả ra tk hr không còn user wishlist nữa hoặc mảng rỗng
        List<WishlistCandidate> getDeleteWc = wishlistCandidateRepository.findByUserHr_Id(wishlistCandidateKey.getHrId());
        return wishlistCandidateMapper.mappedToWishlistCandidateDTO(getDeleteWc);

    }
}
