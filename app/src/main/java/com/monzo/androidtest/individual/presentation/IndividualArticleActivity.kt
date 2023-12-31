package com.monzo.androidtest.individual.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.monzo.androidtest.HeadlinesApp
import com.monzo.androidtest.R
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class IndividualArticleActivity : AppCompatActivity() {
    private lateinit var viewModel: IndividualViewModel

    private lateinit var thumbnailImageView: ImageView
    private lateinit var headlineTextView: TextView
    private lateinit var bodyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_article)

        val url = intent.getStringExtra("EXTRA_URL")
        thumbnailImageView = findViewById(R.id.thumbnail_imageView)
        headlineTextView = findViewById(R.id.headline_textView)
        bodyTextView = findViewById(R.id.body_textView)

        val toolbar = findViewById<Toolbar>(R.id.second_toolbar)
        setSupportActionBar(toolbar)


        viewModel = HeadlinesApp.from2(applicationContext).inject(applicationContext, url!!)

        viewModel.state.observe(this) { state ->
            state?.let {
                updateUI(state)
            }
        }
    }

    private fun updateUI(state: IndividualState) {

        val article = state.article

        headlineTextView.text = article?.headline
        article?.let {
            Glide.with(this).load(it.thumbnail).into(thumbnailImageView)
        }
        val jsonBody = article?.body ?: ""
        val document: Document = Jsoup.parse(jsonBody)
        val formattedText = document.text()
        bodyTextView.text = formattedText
    }
}