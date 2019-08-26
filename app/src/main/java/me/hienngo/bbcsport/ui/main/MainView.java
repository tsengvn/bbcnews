package me.hienngo.bbcsport.ui.main;

import java.util.List;

import me.hienngo.bbcsport.model.NewsModel;

/**
 * @author hienngo
 * @since 2/1/18
 */

public interface MainView {
    void onReceivedData(List<NewsModel> newsModels);

    void onError();

}
