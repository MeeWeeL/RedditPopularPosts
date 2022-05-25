package com.meeweel.redditpopularposts.data

import com.meeweel.redditpopularposts.data.network.ApiService
import com.meeweel.redditpopularposts.domain.RedditPost
import com.meeweel.redditpopularposts.domain.convertResponseToPosts
import io.reactivex.rxjava3.core.Single

class RemoteRepositoryImpl(private val repo: ApiService) : Repository {

    var after: String = ""

    override fun getPosts(): Single<List<RedditPost>> {
        return repo.getPosts(after).map {
            this.after = it.data.after
            convertResponseToPosts(it)
        }
    }
}