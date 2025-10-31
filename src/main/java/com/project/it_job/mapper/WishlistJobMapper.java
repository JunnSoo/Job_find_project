package com.project.it_job.mapper;

import com.project.it_job.dto.JobDTO;
import com.project.it_job.dto.WishlistJobDTO;
import com.project.it_job.entity.Job;
import com.project.it_job.entity.WishlistJob;
import com.project.it_job.entity.auth.User;
import com.project.it_job.keyentity.WishlistJobKey;
import com.project.it_job.request.WishlistJobRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WishlistJobMapper {
    public List<WishlistJobDTO> mappedToWishlistJobDTO(List<WishlistJob> wishlistJobs) {
        List<WishlistJobDTO> result = new ArrayList<>();

        Map<String, List<Job>> groupByUserId = wishlistJobs.stream()
                .collect(Collectors.groupingBy(wj -> wj.getUser().getId(),
                        Collectors.mapping(wj -> wj.getJob(), Collectors.toList())));

        for (Map.Entry<String, List<Job>> entry : groupByUserId.entrySet()) {
            User userWishlist = wishlistJobs.stream().map(wj -> wj.getUser())
                    .filter(user -> user.getId().equalsIgnoreCase(entry.getKey()))
                    .findFirst().orElse(null);

            List<JobDTO> listJobDTO = entry.getValue().stream().map(j -> JobMapper.toDTO(j)).toList();

            WishlistJobDTO  wishlistJobDTO = WishlistJobDTO.builder()
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

    public WishlistJob mappedWishlistJobRequest(User user,Job job ,WishlistJobRequest wishlistJobRequest){
        return WishlistJob.builder()
                .wishlistJobKey(
                        WishlistJobKey.builder()
                                .jobId(wishlistJobRequest.getJobId())
                                .userId(wishlistJobRequest.getUserId())
                                .build()
                )
                .user(user)
                .job(job)
                .build();
    }
}
