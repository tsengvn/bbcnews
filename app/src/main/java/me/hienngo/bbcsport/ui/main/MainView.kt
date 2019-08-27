package me.hienngo.bbcsport.ui.main

import me.hienngo.bbcsport.model.NewsModel

/**
 * @author hienngo
 */

interface MainView {
    fun onReceivedData(newsModels: List<NewsModel>)

    fun onError()
}
