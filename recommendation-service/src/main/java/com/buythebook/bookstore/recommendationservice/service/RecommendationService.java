package com.buythebook.bookstore.recommendationservice.service;

import com.buythebook.bookstore.recommendationservice.dto.response.RecommendationResponse;
import com.buythebook.bookstore.recommendationservice.engine.Recommender;

import com.buythebook.bookstore.recommendationservice.service.common.BookStockTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {
    @Autowired
    Recommender bestPriceRecommender;

    public List<RecommendationResponse> getRecommendationsByGenre(String genre) {
        List<BookStockTrack> bookStockTrackList = bestPriceRecommender.getBestMatch(genre);

        List<RecommendationResponse> responseList = new ArrayList<>();
        for (BookStockTrack track : bookStockTrackList) {
            responseList.add(
                    RecommendationResponse.builder()
                            .bookName(track.getBookName())
                            .genre(genre)
                            .price(track.getPrice())
                            .soldCount(track.getSoldCount()).build());
        }
        return responseList;
    }
}
