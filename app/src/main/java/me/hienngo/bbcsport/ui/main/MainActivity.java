package me.hienngo.bbcsport.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import me.hienngo.bbcsport.NewsApp;
import me.hienngo.bbcsport.R;
import me.hienngo.bbcsport.model.NewsModel;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MainPresenter mainPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsApp.appComponent(this).inject(this);
        setContentView(R.layout.activity_main);
        bindViews();
        mainPresenter.onReceiveView(this);
        mainPresenter.loadData(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }

    @Override
    public void onError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NewsAdapter(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));

        swipeRefreshLayout = findViewById(R.id.swipeRefreshView);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onReceivedData(List<NewsModel> newsModels) {
        NewsAdapter newsAdapter = (NewsAdapter) recyclerView.getAdapter();
        newsAdapter.setData(newsModels);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mainPresenter.loadData(true);
    }
}
