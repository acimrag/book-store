package com.buythebook.bookstore.bookstockservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookStockResponse {
    private String id;
    private String name;
    private String author;
    private String genre;
    private double price;
    private int quantity;
}
