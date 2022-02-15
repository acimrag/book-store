package com.buythebook.bookstore.recommendationservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendationResponse {
    private String bookName;
    private String genre;
    private int soldCount;
    private double price;
}
