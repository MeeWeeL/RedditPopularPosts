package com.meeweel.redditpopularposts.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.redditpopularposts.data.RemoteRepositoryImpl
import com.meeweel.redditpopularposts.data.Repository
import com.meeweel.redditpopularposts.data.network.Retrofit
import com.meeweel.redditpopularposts.domain.RedditPost
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val repo: Repository = RemoteRepositoryImpl(Retrofit().getService())) :
    ViewModel() {

    private val liveDataToObserve: MutableLiveData<List<RedditPost>> = MutableLiveData(listOf())

    fun getData(): LiveData<List<RedditPost>> {
        return liveDataToObserve
    }

    fun requestPosts() = requestPostsFromApi()

    private fun requestPostsFromApi() {
        repo.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataToObserve.postValue(it)
            }, {

            })
    }
}