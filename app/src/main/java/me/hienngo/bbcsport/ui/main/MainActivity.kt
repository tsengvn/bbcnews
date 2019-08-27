package me.hienngo.bbcsport.ui.main

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject

import me.hienngo.bbcsport.NewsApp
import me.hienngo.bbcsport.R
import me.hienngo.bbcsport.model.NewsModel

class MainActivity : AppCompatActivity(), MainView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsApp.appComponent(this).inject(this)
        setContentView(R.layout.activity_main)
        bindViews()
        mainPresenter.onReceiveView(this)
        mainPresenter.loadData(false)
    }


    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDestroy()
    }

    override fun onError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()
    }

    private fun bindViews() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewsAdapter(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))

        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun onReceivedData(newsModels: List<NewsModel>) {
        (recyclerView.adapter as? NewsAdapter)?.apply {
            setData(newsModels)
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onRefresh() {
        mainPresenter.loadData(true)
    }
}
