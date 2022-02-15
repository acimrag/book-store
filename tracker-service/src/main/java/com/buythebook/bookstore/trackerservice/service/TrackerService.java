package com.buythebook.bookstore.trackerservice.service;

import com.buythebook.bookstore.trackerservice.dto.request.BookTrackRequest;
import com.buythebook.bookstore.trackerservice.dto.response.BookTrackResponse;
import com.buythebook.bookstore.trackerservice.model.BookTracker;
import com.buythebook.bookstore.trackerservice.repository.BookTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrackerService {
    @Autowired
    private BookTrackerRepository bookTrackerRepository;

    public String create(BookTrackRequest request) {
        BookTracker bookTracker = BookTracker.builder()
                .bookId(request.getBookId())
                .soldCount(request.getSoldCount())
                .build();

        BookTracker result = bookTrackerRepository.save(bookTracker);
        return result.getId();
    }

    public String update(String id, BookTrackRequest request) {
        BookTracker updatedTracker = BookTracker.builder()
                    .id(id)
                    .bookId(request.getBookId())
                    .soldCount(request.getSoldCount())
                    .build();
        BookTracker result = bookTrackerRepository.save(updatedTracker);
        return result.getId();
    }

    public List<BookTrackResponse> getTrackRecords() {
        List<BookTracker> bookTrackerList = bookTrackerRepository.findAll();
        List<BookTrackResponse> responseList = new ArrayList<>();
        for (BookTracker bookTracker : bookTrackerList) {
            BookTrackResponse response = new BookTrackResponse(bookTracker.getId(), bookTracker.getSoldCount(), bookTracker.getBookId());
            responseList.add(response);
        }
        return responseList;
    }

    public List<BookTrackResponse> getTrackRecordsByBookId(String bookId) {
        BookTracker bookTracker = bookTrackerRepository.findByBookId(bookId).orElseThrow(
                () -> new RuntimeException("Book id doesn't exist:" + bookId));
        List<BookTrackResponse> responseList = new ArrayList<>();

        BookTrackResponse response = new BookTrackResponse(bookTracker.getId(), bookTracker.getSoldCount(), bookTracker.getBookId());
        responseList.add(response);

        return responseList;
    }

    public List<BookTrackResponse> getTrackRecordsByBookIds(List<String> bookIdList) {
        List<BookTracker> bookTrackerList = bookTrackerRepository.findByBookIdIn(bookIdList).orElseThrow(
                () -> new RuntimeException("Failed to get the book id tracks"));
        List<BookTrackResponse> responseList = new ArrayList<>();
        for (BookTracker bookTracker : bookTrackerList) {
            BookTrackResponse response = new BookTrackResponse(bookTracker.getId(),
                    bookTracker.getSoldCount(), bookTracker.getBookId());
            responseList.add(response);
        }
        return responseList;
    }
}
