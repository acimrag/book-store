package com.buythebook.bookstore.bookstockservice.controller;

import com.buythebook.bookstore.bookstockservice.dto.request.CreateBookRequest;
import com.buythebook.bookstore.bookstockservice.dto.response.BookStockResponse;
import com.buythebook.bookstore.bookstockservice.dto.response.SuccessResponse;
import com.buythebook.bookstore.bookstockservice.service.BookStockService;
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
public class BookStockController {

    @Autowired
    private BookStockService bookService;

    @PostMapping
    public ResponseEntity createBook(@RequestBody @Valid CreateBookRequest request) {
        bookService.create(request);
        return ResponseEntity.ok(new SuccessResponse("Adding successful", ""));
    }

    @PutMapping("{id}")
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody @Valid CreateBookRequest request) {
        bookService.update(id, request);
        return ResponseEntity.ok(new SuccessResponse("Update successful", ""));
    }

    @PatchMapping("{id}")
    public ResponseEntity patchBook(@PathVariable String id, @RequestBody CreateBookRequest request) {
        bookService.updatePartial(id, request);
        return ResponseEntity.ok(new SuccessResponse("Update successful", ""));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBook(@PathVariable String id) {
        bookService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Deletion successful", ""));
    }

    @RequestMapping(method = GET, value = {"/", "/genre/{genre}"})
    public ResponseEntity<List<BookStockResponse>> get(@PathVariable Optional<String> genre) {
        List<BookStockResponse> list;
        if (genre.isPresent()) {
            list = bookService.getByGenre(genre.get());
        } else {
            list = bookService.get();
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
