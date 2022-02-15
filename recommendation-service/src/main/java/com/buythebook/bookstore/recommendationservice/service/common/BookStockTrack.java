package com.buythebook.bookstore.recommendationservice.service.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BookStockTrack {
    private String bookId;
    private String bookName;
    private double price;
    private int soldCount;
}
