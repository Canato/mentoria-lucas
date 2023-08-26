package com.monzo.androidtest.articles.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monzo.androidtest.R

import java.util.*


internal class ArticleAdapter(
    private val context: Context,
    listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val articles: MutableList<ArticleItem> = ArrayList()
    private var clickListener: OnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_item_article, parent, false)
        return ArticleViewHolder(view, context, clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val articleViewHolder = holder as ArticleViewHolder
        articleViewHolder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun showArticles(articles: List<ArticleItem>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }


    class ArticleViewHolder(
        view: View,
        private val context: Context,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(view) {
        fun bind(article: ArticleItem) {
            val headlineView = itemView.findViewById<TextView>(R.id.article_headline_textview)
            val thumbnailView = itemView.findViewById<ImageView>(R.id.article_thumbnail_imageview)
            val dateArticleView = itemView.findViewById<TextView>(R.id.article_date_textview)

            headlineView.text = article.title
            dateArticleView.text = article.published
            Glide.with(context).load(article.thumbnail).into(thumbnailView)
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }
}
