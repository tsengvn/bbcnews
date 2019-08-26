package me.hienngo.bbcsport.ui.main;

import android.os.Handler;
import android.text.TextUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.hienngo.bbcsport.domain.interactor.GetNews;

/**
 * @author hienngo
 * @since 2/1/18
 */

public class MainPresenter {
    private MainView mainView;
    private GetNews getNews;
    private Handler handler;
    private String query;

    private Disposable disposable;

    @Inject
    public MainPresenter(GetNews getNews) {
        this.getNews = getNews;
        this.handler = new Handler();
    }

    public void onReceiveView(MainView mainView) {
        this.mainView = mainView;
    }

    public void loadData(boolean refresh) {
        disposable = getNews.getData(refresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsModels -> mainView.onReceivedData(newsModels),
                        throwable -> mainView.onError());
    }

    public void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }

    public void search(CharSequence query) {
        if (!TextUtils.isEmpty(query)) {
            this.query = query.toString();
            handler.removeCallbacks(searchRun);
            handler.postDelayed(searchRun, 500);
        }
    }

    private Runnable searchRun = new Runnable() {
        @Override
        public void run() {
            getNews.search(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(newsModels -> mainView.onReceivedData(newsModels),
                            throwable -> mainView.onError());
        }
    };
}
