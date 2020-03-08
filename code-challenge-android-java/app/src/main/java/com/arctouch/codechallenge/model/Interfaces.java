package com.arctouch.codechallenge.model;

import com.arctouch.codechallenge.view.HomeAdapter;

public class Interfaces {

    public interface UpcomingMovies {
        void getUpcomingMovies(UpcomingMoviesResponse response);
        void updateUpcomingMovies();
    }

    public interface Genres {
        void getGenres(GenreResponse response);
    }

    public interface View {
        void setupRecycler(HomeAdapter homeAdapter);
    }

    public interface OnItemClickListener {
        void onItemClick(Movie selectedMovie);
    }

}
