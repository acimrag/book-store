package com.buythebook.bookstore.trackerservice.repository;

import com.buythebook.bookstore.trackerservice.model.BookTracker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookTrackerRepository extends MongoRepository<BookTracker, String> {
    Optional<BookTracker> findByBookId(String bookId);
    Optional<List<BookTracker>> findByBookIdIn(List<String> ids);

}
