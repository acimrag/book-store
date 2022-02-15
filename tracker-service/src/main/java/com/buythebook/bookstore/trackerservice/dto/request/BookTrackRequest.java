package com.buythebook.bookstore.trackerservice.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class BookTrackRequest {
    @NotNull
    private String bookId;
    @Min(0)
    private int soldCount;
}
