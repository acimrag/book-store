package com.buythebook.bookstore.bookstockservice.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CreateBookRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String author;
    @Min(0)
    private Double price;
    @Min(0)
    private Integer quantity;
    @NotBlank
    private String genre;
}
