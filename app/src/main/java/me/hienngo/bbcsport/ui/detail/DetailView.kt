package me.hienngo.bbcsport.ui.detail

import me.hienngo.bbcsport.model.NewsDetailModel
import me.hienngo.bbcsport.model.NewsModel

/**
 * @author Hien Ngo
 * @since 2019-08-28
 */
interface DetailView {
    fun onReceivedData(newsDetailModel: NewsDetailModel)

    fun onError()
}