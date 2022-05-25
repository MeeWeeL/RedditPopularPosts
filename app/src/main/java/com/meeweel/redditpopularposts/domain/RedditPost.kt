package com.meeweel.redditpopularposts.domain

data class RedditPost(
    val title: String,
    val score: Int,
    val comments: Int
    )