package com.project.codinviec.mapper;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.dto.WishlistJobDTO;
import com.project.codinviec.entity.Job;
import com.project.codinviec.entity.WishlistJob;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.entity.key.WishlistJobKey;
import com.project.codinviec.request.WishlistJobRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WishlistJobMapper {
        private final JobMapper jobMapper;

        public List<WishlistJobDTO> mappedToWishlistJobDTO(List<WishlistJob> wishlistJobs) {
                List<WishlistJobDTO> result = new ArrayList<>();

                Map<String, List<Job>> groupByUserId = wishlistJobs.stream()
                                .collect(Collectors.groupingBy(wj -> wj.getUser().getId(),
                                                Collectors.mapping(WishlistJob::getJob, Collectors.toList())));

                for (Map.Entry<String, List<Job>> entry : groupByUserId.entrySet()) {
                        User userWishlist = wishlistJobs.stream().map(WishlistJob::getUser)
                                        .filter(user -> user.getId().equalsIgnoreCase(entry.getKey()))
                                        .findFirst().orElse(null);

                        List<JobDTO> listJobDTO = entry.getValue().stream().map(jobMapper::toDTO).toList();

                        WishlistJobDTO wishlistJobDTO = WishlistJobDTO.builder()
                                        .idUser(userWishlist.getId())
                                        .firstName(userWishlist.getFirstName())
                                        .lastName(userWishlist.getLastName())
                                        .avatar(userWishlist.getAvatar())
                                        .listJobDTOS(listJobDTO)
                                        .build();
                        result.add(wishlistJobDTO);
                }
                return result;
        }

        public WishlistJob saveWishlistJob(User user, Job job, WishlistJobRequest wishlistJobRequest) {
                if (wishlistJobRequest == null)
                        return null;
                return WishlistJob.builder()
                                .wishlistJobKey(
                                                WishlistJobKey.builder()
                                                                .jobId(wishlistJobRequest.getJobId())
                                                                .userId(wishlistJobRequest.getUserId())
                                                                .build())
                                .user(user)
                                .job(job)
                                .build();
        }
}
