package com.arctouch.codechallenge.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import static com.arctouch.codechallenge.presenter.HomeActivityPresenter.SELECTED_MOVIE;

public class MovieDetailsActivity extends AppCompatActivity {

    private final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();
    private Movie selectedMovie;
    private ImageView backdrop, poster;
    private TextView tvOverview, tvTitle, tvGenres, tvReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getExtras();
        setViews();
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedMovie = (Movie) extras.getSerializable(SELECTED_MOVIE);
        }
    }

    private void setViews(){
        backdrop = findViewById(R.id.iv_backdrop);
        poster = findViewById(R.id.iv_poster);
        tvOverview = findViewById(R.id.tv_overview);
        tvTitle = findViewById(R.id.tv_title);
        tvGenres = findViewById(R.id.tv_genres);
        tvReleaseDate = findViewById(R.id.tv_release_date);

        tvOverview.setText(selectedMovie.overview);
        tvTitle.setText(selectedMovie.title);
        tvReleaseDate.setText(selectedMovie.releaseDate);
        tvGenres.setText(selectedMovie.genresToString());

        setImages();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(selectedMovie.title);
    }

    private void setImages(){
        Glide.with(this)
                .load(movieImageUrlBuilder.buildPosterUrl(selectedMovie.backdropPath))
                .apply(new RequestOptions()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .placeholder(R.drawable.ic_image_placeholder))
                .into(backdrop);

        Glide.with(this)
                .load(movieImageUrlBuilder.buildPosterUrl(selectedMovie.posterPath))
                .apply(new RequestOptions()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .placeholder(R.drawable.ic_image_placeholder)
                        .override(400,450))
                .into(poster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
