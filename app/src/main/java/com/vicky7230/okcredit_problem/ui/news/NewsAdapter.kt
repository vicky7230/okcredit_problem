package com.vicky7230.okcredit_problem.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vicky7230.okcredit_problem.R
import com.vicky7230.okcredit_problem.data.Article
import com.vicky7230.okcredit_problem.ui.news.NewsAdapter.NewsViewHolder
import com.vicky7230.okcredit_problem.utils.CommonUtils.isValidUrl
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsAdapter(private var newsArrayList: ArrayList<Article>) :
    RecyclerView.Adapter<NewsViewHolder>() {
    interface Callback {
        fun onItemClick(article: Article)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback?) {
        this.callback = callback
    }

    fun updateItems(newsArrayList: ArrayList<Article>) {
        this.newsArrayList.clear()
        this.newsArrayList = newsArrayList
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<Article> {
        return newsArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsViewHolder = NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        )
        newsViewHolder.itemView.setOnClickListener { v: View? ->
            val position = newsViewHolder.adapterPosition
            if (callback != null) {
                callback!!.onItemClick(newsArrayList[position])
            }
        }
        return newsViewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(newsArrayList[position])
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    inner class NewsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun onBind(news: Article) {
            if (isValidUrl(news.imageUrl)) {
                Glide.with(itemView.context)
                    .load(news.imageUrl)
                    .error(R.drawable.ic_alert_black_48dp)
                    .transform(CenterCrop(), RoundedCorners(25))
                    .into(itemView.image)

            } else {
                itemView.image.setImageResource(R.drawable.ic_alert_black_48dp)
            }

            itemView.title.text = news.title
            itemView.author.text = news.author
        }
    }

}