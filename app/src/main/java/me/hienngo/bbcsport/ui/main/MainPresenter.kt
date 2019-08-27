package me.hienngo.bbcsport.ui.main

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.hienngo.bbcsport.domain.interactor.GetNews

/**
 * @author hienngo
 */
class MainPresenter @Inject constructor(private val getNews: GetNews) {
    private var mainView: MainView? = null

    private var disposable: Disposable? = null

    fun onReceiveView(mainView: MainView) {
        this.mainView = mainView
    }

    fun loadData(refresh: Boolean) {
        disposable = getNews.getData(refresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newsModels -> mainView?.onReceivedData(newsModels) },
                        { throwable -> mainView?.onError() })
    }

    fun onDestroy() {
        disposable?.dispose()
    }

}
