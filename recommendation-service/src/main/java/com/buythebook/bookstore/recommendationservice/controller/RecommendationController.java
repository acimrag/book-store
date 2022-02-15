package com.buythebook.bookstore.recommendationservice.controller;

import com.buythebook.bookstore.recommendationservice.dto.response.RecommendationResponse;
import com.buythebook.bookstore.recommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
public class RecommendationController {
    @Autowired
    RecommendationService recommendationService;

    @RequestMapping(method = GET, value = {"/{genre}"})
    public ResponseEntity<List<RecommendationResponse>> getRecommendation(@PathVariable Optional<String> genre) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByGenre(genre.get()));
    }
}
