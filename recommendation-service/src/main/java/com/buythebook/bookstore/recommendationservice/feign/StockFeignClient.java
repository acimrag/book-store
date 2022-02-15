package com.buythebook.bookstore.recommendationservice.feign;

import com.buythebook.bookstore.recommendationservice.dto.response.BookStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient("stock-service")
public interface StockFeignClient {
    @GetMapping("/genre/{genre}")
    List<BookStockResponse> get(@PathVariable("genre") String genre);
}
