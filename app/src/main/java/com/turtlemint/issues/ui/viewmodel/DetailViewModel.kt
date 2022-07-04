package com.turtlemint.issues.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.turtlemint.issues.data.model.CommentDetails
import com.turtlemint.issues.data.model.CommentList
import com.turtlemint.issues.data.repo.DetailRepository
import com.turtlemint.issues.data.datasource.RemoteDataSource
import com.turtlemint.issues.data.datasource.Result
import com.turtlemint.issues.data.service.Network

class DetailViewModel : ViewModel() {
    val detailState = mutableStateOf(listOf<CommentDetails>())
    val errorState = mutableStateOf("")
    private val detailRepository = DetailRepository(RemoteDataSource(Network.getApiService()))

    suspend fun fetchCommentsList(id: Int) {
        detailRepository.fetchCommentList(id).collect {
            when (it) {
                is Result.Success<*> -> detailState.value = (it.data as CommentList)
                else -> errorState.value = "Unable to fetch issues..."
            }
        }
    }
}