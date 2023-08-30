package com.monzo.androidtest.individual.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.monzo.androidtest.R

class IndividualArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_article)

        val url = intent.getStringExtra("EXTRA_URL")
        val urlText = findViewById<TextView>(R.id.headline_textView)
        urlText.text = url


    }
}