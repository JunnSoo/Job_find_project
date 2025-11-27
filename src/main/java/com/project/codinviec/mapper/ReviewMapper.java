package com.project.codinviec.mapper;

import com.project.codinviec.dto.ReviewDTO;
import com.project.codinviec.dto.UserReviewDTO;
import com.project.codinviec.entity.Review;
import com.project.codinviec.entity.auth.Company;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.mapper.auth.UserMapper;
import com.project.codinviec.request.SaveUpdateReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewMapper {
    private final UserMapper userMapper;

    public ReviewDTO reviewToReviewDTO(Review review, boolean getUsers){
        return ReviewDTO.builder()
                .id(review.getId())
                .title(review.getTitle())
                .description(review.getDescription())
                .rated(review.getRated())
                .user(getUsers ? userMapper.userToUserReviewDTO(review.getUser()) : null )
                .build();
    }


    public Review saveReviewMapper(User user, Company company, SaveUpdateReviewRequest saveUpdateReviewRequest){
        return Review.builder()
                .title(saveUpdateReviewRequest.getTitle())
                .description(saveUpdateReviewRequest.getDescription())
                .rated(saveUpdateReviewRequest.getRated())
                .user(user)
                .company(company)
                .build();
    }

    public Review updateReviewMapper(Integer idReview ,User user, Company company, SaveUpdateReviewRequest saveUpdateReviewRequest){
        return Review.builder()
                .id(idReview)
                .title(saveUpdateReviewRequest.getTitle())
                .description(saveUpdateReviewRequest.getDescription())
                .rated(saveUpdateReviewRequest.getRated())
                .user(user)
                .company(company)
                .build();
    }

    public UserReviewDTO reviewToUserReviewDTO(User user, List<ReviewDTO> reviews){
        return UserReviewDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(user.getAvatar())
                .listReviews(reviews)
                .build();
    }
}
