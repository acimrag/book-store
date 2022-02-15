package com.buythebook.bookstore.recommendationservice.engine.impl;

import com.buythebook.bookstore.recommendationservice.dto.response.BookStockResponse;
import com.buythebook.bookstore.recommendationservice.dto.response.BookTrackResponse;
import com.buythebook.bookstore.recommendationservice.engine.Recommender;
import com.buythebook.bookstore.recommendationservice.feign.StockFeignClient;
import com.buythebook.bookstore.recommendationservice.feign.TrackFeignClient;
import com.buythebook.bookstore.recommendationservice.service.common.BookStockTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BestPriceRecommender implements Recommender {
    @Autowired
    StockFeignClient stockFeignClient;

    @Autowired
    TrackFeignClient trackFeignClient;

    @Value("${application.recommender.bestprice.maxcount}")
    private int matchCount;


    @Override
    public List getBestMatch(Object param) {
        List<BookStockResponse> bookStockResponseList = stockFeignClient.get((String) param);

        String[] bookIds = bookStockResponseList.stream().map(x -> x.getId()).toArray(String[]::new);
        List<BookTrackResponse> bookTrackResponseList = trackFeignClient.get(bookIds);

        return getRecommendationList(bookStockResponseList, bookTrackResponseList)
                .stream()
                .limit(matchCount)
                .collect(Collectors.toList());
    }

    private List<BookStockTrack> getRecommendationList(List<BookStockResponse> bookStockResponseList, List<BookTrackResponse> bookTrackResponseList) {
        List<BookStockTrack> bookStockTrackList = createBookStockTrack(bookStockResponseList, bookTrackResponseList);
        bookStockTrackList.sort(Comparator.comparing(BookStockTrack::getPrice).thenComparing(BookStockTrack::getSoldCount));
        return bookStockTrackList;
    }

    private List<BookStockTrack> createBookStockTrack(List<BookStockResponse> bookStockResponseList, List<BookTrackResponse> bookTrackResponseList) {
        Map<String, BookStockTrack> bookStockTrackMap = createTrackSoldCountMap(bookTrackResponseList);

        List<BookStockTrack> dataMergerList = new ArrayList<>();

        for(BookStockResponse bookStockResponse : bookStockResponseList) {
            BookStockTrack dataMerger = bookStockTrackMap.get(bookStockResponse.getId());
            if(dataMerger != null) {
                dataMerger.setPrice(bookStockResponse.getPrice());
                dataMerger.setBookName(bookStockResponse.getName());
                dataMergerList.add(dataMerger);
            } else  {
                dataMergerList.add(BookStockTrack.builder()
                        .bookId(bookStockResponse.getId())
                        .bookName(bookStockResponse.getName())
                        .price(bookStockResponse.getPrice())
                        .soldCount(0)
                        .build());
            }
        }
        return dataMergerList;
    }

    private Map<String, BookStockTrack> createTrackSoldCountMap(List<BookTrackResponse> bookTrackResponseList) {
        Map<String, BookStockTrack> bookStockTrackMap = new HashMap();
        for (BookTrackResponse bookTrackResponse : bookTrackResponseList) {
            bookStockTrackMap.put(bookTrackResponse.getBookId(),
                    BookStockTrack.builder()
                            .bookId(bookTrackResponse.getBookId())
                            .soldCount(bookTrackResponse.getSoldCount())
                            .build());
        }
        return bookStockTrackMap;
    }
}
