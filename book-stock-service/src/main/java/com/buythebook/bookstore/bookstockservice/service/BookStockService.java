package com.buythebook.bookstore.bookstockservice.service;

import com.buythebook.bookstore.bookstockservice.dto.request.CreateBookRequest;
import com.buythebook.bookstore.bookstockservice.dto.response.BookStockResponse;
import com.buythebook.bookstore.bookstockservice.model.BookStock;
import com.buythebook.bookstore.bookstockservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookStockService {
    @Autowired
    BookRepository bookRepository;

    public String create(CreateBookRequest request) {
        BookStock bookStock = BookStock.builder()
                .author(request.getAuthor())
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .genre(request.getGenre())
                .build();
        BookStock result = bookRepository.save(bookStock);
        return result.getId();
    }

    public String update(String id, CreateBookRequest request) {
        BookStock bookStock = BookStock.builder()
                .id(id)
                .author(request.getAuthor())
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .genre(request.getGenre())
                .build();
        BookStock result = bookRepository.save(bookStock);
        return result.getId();
    }

    public String updatePartial(String id, CreateBookRequest request) {
        BookStock bookStock = bookRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Book doesn't exist with id:" + id));
        if(request.getQuantity() != null) {
            bookStock.setQuantity(request.getQuantity());
        }
        bookRepository.save(bookStock);
        return bookStock.getId();
    }

    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    public List<BookStockResponse> getByGenre(String genre) {
        List<BookStockResponse> bookStockResponseList = new ArrayList<>();

        List<BookStock> bookStockList = bookRepository.findByGenreOrderByPriceDesc(genre).orElseThrow(() ->
                new RuntimeException("Book doesn't exist with genre:" + genre));

        for (BookStock bookStock : bookStockList) {
            BookStockResponse bookStockResponse = BookStockResponse.builder()
                    .id(bookStock.getId())
                    .genre(bookStock.getGenre())
                    .name(bookStock.getName())
                    .author(bookStock.getAuthor())
                    .price(bookStock.getPrice())
                    .quantity(bookStock.getQuantity())
                    .build();
            bookStockResponseList.add(bookStockResponse);
        }

        return bookStockResponseList;
    }

    public List<BookStockResponse> get() {
        List<BookStockResponse> bookStockResponseList = new ArrayList<>();

        List<BookStock> bookStockList = bookRepository.findAll();

        for (BookStock bookStock : bookStockList) {
            BookStockResponse bookStockResponse = BookStockResponse.builder()
                    .id(bookStock.getId())
                    .genre(bookStock.getGenre())
                    .name(bookStock.getName())
                    .author(bookStock.getAuthor())
                    .price(bookStock.getPrice())
                    .quantity(bookStock.getQuantity())
                    .build();
            bookStockResponseList.add(bookStockResponse);
        }

        return bookStockResponseList;
    }
}
