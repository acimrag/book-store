package com.buythebook.bookstore.recommendationservice.engine;

import java.util.List;

public interface Recommender<R, P> {
    List<R> getBestMatch(P param);
}
