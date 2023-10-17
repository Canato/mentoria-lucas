package com.monzo.androidtest.articles.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monzo.androidtest.R
import kotlin.collections.ArrayList

internal class SectionAdapter(
    private val context: Context,
    private val listener: ArticleAdapter.OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val sections: MutableList<ArticleSection> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.activity_article_section, parent, false)
        return SectionViewHolder(view, context, listener)
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sectionViewHolder = holder as SectionViewHolder
        sectionViewHolder.bind(sections[position])
    }

    fun showSections(sections: List<ArticleSection>) {
        this.sections.clear()
        this.sections.addAll(sections)
        notifyDataSetChanged()

    }

    class SectionViewHolder(
        view: View,
        private val context: Context,
        private val listener: ArticleAdapter.OnItemClickListener
    ) : RecyclerView.ViewHolder(view) {
        fun bind(section: ArticleSection) {
            val title = itemView.findViewById<TextView>(R.id.article_date_textview)
            val recyclerView = itemView.findViewById<RecyclerView>(R.id.sections_recyclerview)

            title.text = section.title

            val adapter = ArticleAdapter(
                context = context,
                listener = listener
            )

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            adapter.showArticles(section.articles)
        }
    }
}