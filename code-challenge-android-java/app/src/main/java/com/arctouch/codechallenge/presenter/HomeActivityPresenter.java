package com.arctouch.codechallenge.presenter;

import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.GenreResponse;
import com.arctouch.codechallenge.model.Interfaces;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.TmdbApiHelper;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;
import com.arctouch.codechallenge.view.HomeActivity;
import com.arctouch.codechallenge.view.HomeAdapter;

import java.util.ArrayList;

public class HomeActivityPresenter implements Interfaces.UpcomingMovies, Interfaces.Genres {

    private HomeActivity homeActivity;
    private TmdbApiHelper apiHelper;

    public HomeActivityPresenter(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    public void setupHomeAdapter() {
        apiHelper = new TmdbApiHelper(this);
        apiHelper.requestGenres();
    }

    @Override
    public void getUpcomingMovies(UpcomingMoviesResponse response) {
        for (Movie movie : response.results) {
            movie.genres = new ArrayList<>();
            for (Genre genre : Cache.getGenres()) {
                if (movie.genreIds.contains(genre.id)) {
                    movie.genres.add(genre);
                }
            }
        }
        homeActivity.setupRecycler(new HomeAdapter(response.results));
    }

    @Override
    public void getGenres(GenreResponse response) {
        Cache.setGenres(response.genres);
        apiHelper.requestUpComingMovie();
    }
}
