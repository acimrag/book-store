package com.buythebook.bookstore.bookstockservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@Document(collection = "books")
public class BookStock {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String author;
    @NotBlank
    private String genre;
    @Min(0)
    private double price;
    @Min(0)
    private int quantity;
}
