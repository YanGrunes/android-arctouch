package com.arctouch.codechallenge.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.TmdbApi;
import com.arctouch.codechallenge.presenter.Cache;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Cache.setGenres(response.genres);
                    startActivity(new Intent(this, HomeActivity.class));
                    finish();
                });
    }
}
