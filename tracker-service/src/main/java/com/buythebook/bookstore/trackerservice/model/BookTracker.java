package com.buythebook.bookstore.trackerservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Document(collection = "booktracker")
public class BookTracker {
    @Id
    private String id;
    @NotNull
    private String bookId;
    @NotNull
    private int soldCount;
}
