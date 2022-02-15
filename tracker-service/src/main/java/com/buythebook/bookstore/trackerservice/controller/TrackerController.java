package com.buythebook.bookstore.trackerservice.controller;

import com.buythebook.bookstore.trackerservice.dto.request.BookTrackRequest;
import com.buythebook.bookstore.trackerservice.dto.response.BookTrackResponse;
import com.buythebook.bookstore.trackerservice.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
public class TrackerController {
    @Autowired
    TrackerService trackerService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid BookTrackRequest request) {
        trackerService.create(request);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody @Valid BookTrackRequest request) {
        trackerService.update(id, request);
        return ResponseEntity.ok("Success");
    }

    @RequestMapping(method = GET, value = {"/", "/{bookids}"})
    public ResponseEntity<List<BookTrackResponse>> get(@PathVariable Optional<List<String>> bookids) {
        List<BookTrackResponse> list;
        if (bookids.isPresent()) {
            list = trackerService.getTrackRecordsByBookIds(bookids.get());
        } else {
            list = trackerService.getTrackRecords();
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
