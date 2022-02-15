package com.buythebook.bookstore.recommendationservice.feign;

import com.buythebook.bookstore.recommendationservice.dto.response.BookTrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient("tracker-service")
public interface TrackFeignClient {
    @GetMapping("/{bookids}")
    List<BookTrackResponse> get(@PathVariable("bookids") String[] bookids);
}
