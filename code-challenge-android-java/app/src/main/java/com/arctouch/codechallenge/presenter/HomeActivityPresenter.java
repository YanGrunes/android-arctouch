package com.arctouch.codechallenge.presenter;

import android.widget.Toast;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.GenreResponse;
import com.arctouch.codechallenge.model.Interfaces;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.TmdbApiHelper;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;
import com.arctouch.codechallenge.view.HomeActivity;
import com.arctouch.codechallenge.view.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivityPresenter implements Interfaces.UpcomingMovies, Interfaces.Genres {

    private HomeActivity homeActivity;
    private HomeAdapter homeAdapter;
    private TmdbApiHelper apiHelper;
    private int currentPage;
    private int lastPage;
    private boolean isFirstRequest;

    public HomeActivityPresenter(HomeActivity homeActivity) {
        currentPage = 1;
        this.homeActivity = homeActivity;
    }

    public void setupHomeAdapter() {
        apiHelper = new TmdbApiHelper(this);
        apiHelper.requestGenres();
    }

    @Override
    public void getUpcomingMovies(UpcomingMoviesResponse response) {
        if (response != null) {
            lastPage = response.totalPages;
            if (response.results != null && response.results.size() > 0) {
                updateGenres(response.results);
                if (homeAdapter == null) {
                    homeAdapter = new HomeAdapter(response.results);
                    homeActivity.setupRecycler(homeAdapter);
                } else {
                    homeAdapter.getMovies().addAll(response.results);
                    homeAdapter.notifyDataSetChanged();
                }
                if (!isFirstRequest) {
                    currentPage++;
                }
            }
        }
    }

    private void updateGenres(List<Movie> movies){
        for (Movie movie : movies) {
            movie.genres = new ArrayList<>();
            for (Genre genre : Cache.getGenres()) {
                if (movie.genreIds.contains(genre.id)) {
                    movie.genres.add(genre);
                }
            }
        }
    }

    @Override
    public void updateUpcomingMovies() {
        if(currentPage + 1 < lastPage) {
            isFirstRequest = false;
            apiHelper.requestUpComingMovie(currentPage + 1);
        } else {
            Toast.makeText(homeActivity, homeActivity.getText(R.string.msg_last_page), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getGenres(GenreResponse response) {
        isFirstRequest = true;
        Cache.setGenres(response.genres);
        apiHelper.requestUpComingMovie(currentPage);
    }
}
