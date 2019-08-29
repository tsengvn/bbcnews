package me.hienngo.bbcsport.ui.detail

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.hienngo.bbcsport.domain.interactor.GetNewsDetail
import javax.inject.Inject

/**
 * @author hienngon
 */
class DetailPresenter @Inject constructor(private val getNewsDetail: GetNewsDetail) {
    private var detailView: DetailView? = null

    fun onReceiveView(detailView: DetailView) {
        this.detailView = detailView
    }

    fun load(title: String, author: String, publishedAt: String) {
        getNewsDetail.getNews(title, author, publishedAt)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { detailView?.onReceivedData(it) }
                .doOnError { detailView?.onError() }
                .subscribe()
    }
}