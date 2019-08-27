package me.hienngo.bbcsport.ui.main

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import java.util.ArrayList

import me.hienngo.bbcsport.R
import me.hienngo.bbcsport.model.NewsModel
import me.hienngo.bbcsport.ui.detail.WebviewActivity

/**
 * @author hienngo
 */

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    private val newsModels: MutableList<NewsModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val model = newsModels[position]
        holder.titleView.text = model.title
        holder.timestampView.text = model.timestamp
        holder.descView.text = model.description
        Picasso.with(context).load(model.imageUrl).fit().centerCrop().into(holder.imageView)

        holder.itemView.setOnClickListener { view -> WebviewActivity.open(context, model.title, model.webUrl) }
    }

    override fun getItemCount(): Int {
        return newsModels.size
    }

    fun setData(newsModels: List<NewsModel>) {
        this.newsModels.clear()
        this.newsModels.addAll(newsModels)
        notifyDataSetChanged()
    }

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.ivImage)
        var titleView: TextView = itemView.findViewById(R.id.tvTitle)
        var descView: TextView = itemView.findViewById(R.id.tvDesc)
        var timestampView: TextView = itemView.findViewById(R.id.tvTimestamp)
    }
}
