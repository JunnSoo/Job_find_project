package com.project.it_job.service.imp;

import com.project.it_job.dto.WishlistJobDTO;
import com.project.it_job.entity.Job;
import com.project.it_job.entity.WishlistJob;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.exception.ParamExceptionHandler;
import com.project.it_job.mapper.WishlistJobMapper;
import com.project.it_job.repository.JobRepository;
import com.project.it_job.repository.WishlistJobRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.WishlistJobRequest;
import com.project.it_job.service.WishlistJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistJobServiceImp implements WishlistJobService {
    private final WishlistJobRepository wishlistJobRepository;
    private final WishlistJobMapper wishlistJobMapper;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public List<WishlistJobDTO> getAllWishlistJobs() {
        return wishlistJobMapper.mappedToWishlistJobDTO(wishlistJobRepository.findAll());
    }

    @Override
    public Page<WishlistJobDTO> getAllWishlistJobsPage(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageSize() == 0) {
            throw new ParamExceptionHandler("page size truyền lên không hợp lệ");
        }
        Pageable pageable = PageRequest.of(pageRequestCustom.getPageNumber() - 1, pageRequestCustom.getPageSize());
        Page<WishlistJob> wishlistJobPage = wishlistJobRepository.findAll(pageable);
        List<WishlistJobDTO> listWishlistJobDTO = wishlistJobMapper.mappedToWishlistJobDTO(wishlistJobPage.getContent());
        return new PageImpl<>(listWishlistJobDTO, pageable, wishlistJobPage.getTotalElements());
    }

    @Override
    public List<WishlistJobDTO> getWishlistJobsByUserId(String userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy user id"));
        return wishlistJobMapper.mappedToWishlistJobDTO(wishlistJobRepository.findByUser_Id(userId));
    }

    @Override
    public List<WishlistJobDTO> saveWishlistJobs(WishlistJobRequest wishlistJobRequest) {
        User user =  userRepository.findById(wishlistJobRequest.getUserId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id user"));
        Job job = jobRepository.findById(wishlistJobRequest.getJobId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id job"));

        WishlistJob wishlistJob = wishlistJobMapper.saveWishlistJob(user, job, wishlistJobRequest);
        wishlistJobRepository.save(wishlistJob);
        return wishlistJobMapper.mappedToWishlistJobDTO(wishlistJobRepository.findByUser_Id(user.getId()));
    }

    @Override
    @Transactional
    public List<WishlistJobDTO> deleteWishlistJobs(WishlistJobRequest wishlistJobRequest) {
        User user =  userRepository.findById(wishlistJobRequest.getUserId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id user"));
        Job job = jobRepository.findById(wishlistJobRequest.getJobId())
                .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id job"));
        WishlistJob wishlistJob = wishlistJobMapper.saveWishlistJob(user, job, wishlistJobRequest);
        wishlistJobRepository.delete(wishlistJob);
        return wishlistJobMapper.mappedToWishlistJobDTO(wishlistJobRepository.findByUser_Id(user.getId()));
    }
}
