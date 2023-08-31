package com.monzo.androidtest.individual.presentation



data class IndividualState(
    val article: IndividualArticle? = null
)

data class IndividualArticle(
    val thumbnail : String,
    val headline : String,
    val text : String
)