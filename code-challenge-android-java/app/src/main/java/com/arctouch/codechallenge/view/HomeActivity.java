package com.arctouch.codechallenge.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Interfaces;
import com.arctouch.codechallenge.presenter.HomeActivityPresenter;

public class HomeActivity extends AppCompatActivity implements Interfaces.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private HomeActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        this.recyclerView = findViewById(R.id.recyclerView);
        this.progressBar = findViewById(R.id.progressBar);
        presenter = new HomeActivityPresenter(this);
        presenter.setupHomeAdapter();
    }

    @Override
    public void setupRecycler(HomeAdapter homeAdapter) {
        recyclerView.setAdapter(homeAdapter);
        progressBar.setVisibility(View.GONE);
    }
}
