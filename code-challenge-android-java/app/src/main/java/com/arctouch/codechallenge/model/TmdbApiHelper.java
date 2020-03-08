package com.arctouch.codechallenge.model;

import com.arctouch.codechallenge.presenter.HomeActivityPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TmdbApiHelper {

    private Object presenter;

    public TmdbApiHelper(Object presenter) {
        this.presenter = presenter;
    }

    public void requestUpComingMovie(int currentPage) {
        TmdbApiSingleton.getInstance().getApi()
                .upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, new Long(currentPage),"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> ((HomeActivityPresenter) presenter).getUpcomingMovies(response));
    }

    public void requestGenres() {
        TmdbApiSingleton.getInstance().getApi().
                genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> ((HomeActivityPresenter) presenter).getGenres(response));
    }
}
