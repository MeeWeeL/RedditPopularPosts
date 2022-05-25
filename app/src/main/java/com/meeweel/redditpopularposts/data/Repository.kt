package com.meeweel.redditpopularposts.data

import com.meeweel.redditpopularposts.domain.RedditPost
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getPosts(): Single<List<RedditPost>>
}