package com.monzo.androidtest.individual.presentation



data class IndividualState(
    val article: IndividualArticle?
)

data class IndividualArticle(
    val thumbnail : String,
    val headline : String,
    val text : String
)