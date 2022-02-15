package com.buythebook.bookstore.bookstockservice.repository;

import com.buythebook.bookstore.bookstockservice.model.BookStock;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<BookStock, String> {
    Optional<BookStock> findById(String id);

    Optional<List<BookStock>> findByGenreOrderByPriceDesc(String genre);
}
