package com.example.todaysbook.repository;

import com.example.todaysbook.domain.dto.Review;
import com.example.todaysbook.domain.dto.ReviewRequestDto;
import com.example.todaysbook.domain.dto.SimpleReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<Review> getReviews(long bookId, long userId, String orderBy);
    List<SimpleReview> getSimpleReviews();
    int addLikeReview(long userId, long reviewId);
    int addDislikeReview(long userId, long reviewId);
    int deleteLikeReview(long userId, long reviewId);
    int deleteDislikeReview(long userId, long reviewId);
    int countLike(long reviewId);
    int countDislike(long reviewId);
    int addReview(ReviewRequestDto requestDto);
    int deleteReview(long reviewId);
    int updateReview(ReviewRequestDto requestDto);
}
