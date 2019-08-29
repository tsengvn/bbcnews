package me.hienngo.bbcsport.ui.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import me.hienngo.bbcsport.NewsApp
import me.hienngo.bbcsport.R
import me.hienngo.bbcsport.model.NewsDetailModel
import me.hienngo.bbcsport.ui.main.MainPresenter
import javax.inject.Inject

/**
 * @author hienngo
 */
class DetailActivity : AppCompatActivity(), DetailView {
    @Inject
    lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsApp.appComponent(this).inject(this)
        setContentView(R.layout.activity_detail)
        detailPresenter.onReceiveView(this)
        detailPresenter.load(intent.getStringExtra("title"),
                intent.getStringExtra("author"), intent.getStringExtra("publishedAt"))
    }

    override fun onReceivedData(newsDetailModel: NewsDetailModel) {
        actionBar?.title = newsDetailModel.title
        tvContent.text = newsDetailModel.content
    }

    override fun onError() {
    }

    companion object {
        fun open(context: Context, title: String, author: String, publishedAt: String) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title", title)
                putExtra("author", author)
                putExtra("publishedAt", publishedAt)
            }
            context.startActivity(intent)
        }
    }
}