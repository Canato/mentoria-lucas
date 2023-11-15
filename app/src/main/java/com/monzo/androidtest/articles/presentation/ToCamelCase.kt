package com.monzo.androidtest.articles.presentation

fun String.toCamelCase(): String {
    val words = this.replace("[ _-]".toRegex(), " ").splitToSequence(" ")


    return words.joinToString("") {
        if (it == words.first()) it.replaceFirstChar(Char::lowercaseChar)
        else it.replaceFirstChar (Char::uppercaseChar)
    }

//    return buildString {
//        words.forEachIndexed { index, word ->
//            append(
//                if (index == 0) word.replaceFirstChar{it.lowercase()}
//                else word.replaceFirstChar{it.uppercase()}
//            )
//        }
//    }
}
