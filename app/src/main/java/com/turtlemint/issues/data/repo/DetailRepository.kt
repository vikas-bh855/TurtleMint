package com.turtlemint.issues.data.repo

import com.turtlemint.issues.data.datasource.RemoteDataSource
import kotlinx.coroutines.flow.flow

class DetailRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun fetchCommentList(id: Int) = flow {
        emit(remoteDataSource.fetchComments(id))
    }
}