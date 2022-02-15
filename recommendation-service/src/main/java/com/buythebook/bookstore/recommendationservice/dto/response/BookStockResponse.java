package com.buythebook.bookstore.recommendationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStockResponse {
    private String id;
    private String name;
    private String author;
    private String genre;
    private double price;
    private int quantity;
}
