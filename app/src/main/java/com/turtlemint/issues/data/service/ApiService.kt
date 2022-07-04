package com.turtlemint.issues.data.service

import com.turtlemint.issues.data.model.CommentList
import com.turtlemint.issues.data.model.IssueList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("issues")
    suspend fun fetchIssue(): Response<IssueList>

    @GET("issues/{id}/comments")
    suspend fun fetchComments(@Path("id") id: Int): Response<CommentList>
}