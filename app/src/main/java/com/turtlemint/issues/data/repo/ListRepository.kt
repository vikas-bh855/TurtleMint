package com.turtlemint.issues.data.repo

import com.turtlemint.issues.data.datasource.RemoteDataSource
import kotlinx.coroutines.flow.flow

class ListRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun fetchIssueList() = flow {
       emit(remoteDataSource.fetchIssues())
    }
}