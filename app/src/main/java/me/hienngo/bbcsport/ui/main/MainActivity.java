package me.hienngo.bbcsport.ui.main;

import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;
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
    private SearchView searchView;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("@nmh", "query " + newText);
                mainPresenter.search(searchView.getQuery());
                return true;
            }
        });
        return true;
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
        Log.v("@nmh", "onReceivedData " + newsModels.size());
        NewsAdapter newsAdapter = (NewsAdapter) recyclerView.getAdapter();
        newsAdapter.setData(newsModels);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mainPresenter.loadData(true);
    }
}
