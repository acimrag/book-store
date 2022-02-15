package com.buythebook.bookstore.trackerservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookTrackResponse {
    private String id;
    private int soldCount;
    private String bookId;
}
