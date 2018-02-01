package me.hienngo.bbcsport.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.hienngo.bbcsport.R;
import me.hienngo.bbcsport.model.NewsModel;

/**
 * @author hienngo
 * @since 2/1/18
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private final Context context;
    private List<NewsModel> newsModels;
    public NewsAdapter(Context context) {
        this.context = context;
        this.newsModels = new ArrayList<>();
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        NewsModel model = newsModels.get(position);
        holder.titleView.setText(model.getTitle());
        holder.timestampView.setText(model.getTimestamp());
        holder.descView.setText(model.getDescription());
        Picasso.with(context).load(model.getImageUrl()).fit().centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public void setData(List<NewsModel> newsModels) {
        this.newsModels.clear();
        this.newsModels.addAll(newsModels);
        notifyDataSetChanged();
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView, descView, timestampView;
        public NewsHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivImage);
            timestampView = itemView.findViewById(R.id.tvTimestamp);
            titleView = itemView.findViewById(R.id.tvTitle);
            descView = itemView.findViewById(R.id.tvDesc);
        }
    }
}
