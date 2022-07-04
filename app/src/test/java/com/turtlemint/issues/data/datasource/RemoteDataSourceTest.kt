package com.turtlemint.issues.data.datasource

import com.turtlemint.issues.data.model.CommentList
import com.turtlemint.issues.data.model.IssueList
import com.turtlemint.issues.data.service.ApiService
import junit.framework.Assert.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {
    @Mock
    private lateinit var apiService: ApiService

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun `test fetch issues list, success`() = runTest {
        whenever(apiService.fetchIssue()).thenReturn(
            Response.success(IssueList())
        )
        when (val result = remoteDataSource.fetchIssues()) {
            is Result.Success<*> -> {
                Assert.assertEquals(result.data, IssueList())
                verify(apiService, times(1)).fetchIssue()
            }
            is Result.Error<*> -> fail()
        }
    }

    @Test
    fun `test fetch issues list, fail`() = runTest {
        whenever(apiService.fetchIssue()).thenReturn(
            Response.error(400, "Validation failed".toResponseBody())
        )
        when (val result = remoteDataSource.fetchIssues()) {
            is Result.Success<*> -> {
                fail()
            }
            is Result.Error<*> -> {
                Assert.assertEquals(result.code, 400)
                verify(apiService, times(1)).fetchIssue()
            }
        }
    }


    /* Added illegal exception to validate the result when failed to connect
     Whereas it will throw IOException */
    @Test
    fun `test fetch issues list, exception`() = runTest {
        whenever(apiService.fetchIssue()).thenThrow(IllegalArgumentException())
        when (val result = remoteDataSource.fetchIssues()) {
            is Result.Success<*> -> {
                fail()
            }
            is Result.Error<*> -> {
                Assert.assertEquals(result.code, 404)
                verify(apiService, times(1)).fetchIssue()
            }
        }
    }

    @Test
    fun `test fetch comments list, success`() = runTest {
        whenever(apiService.fetchComments(123)).thenReturn(
            Response.success(CommentList())
        )
        when (val result = remoteDataSource.fetchComments(123)) {
            is Result.Success<*> -> {
                Assert.assertEquals(result.data, IssueList())
                verify(apiService, times(1)).fetchComments(123)
            }
            is Result.Error<*> -> fail()
        }
    }

    @Test
    fun `test fetch comments list, fail`() = runTest {
        whenever(apiService.fetchComments(123)).thenReturn(
            Response.error(400, "Validation failed".toResponseBody())
        )
        when (val result = remoteDataSource.fetchComments(123)) {
            is Result.Success<*> -> {
                fail()
            }
            is Result.Error<*> -> {
                Assert.assertEquals(result.code, 400)
                verify(apiService, times(1)).fetchComments(123)
            }
        }
    }


    /* Added illegal exception to validate the result when failed to connect
     Whereas it will throw IOException */
    @Test
    fun `test fetch comments list, exception`() = runTest {
        whenever(apiService.fetchComments(123)).thenThrow(IllegalArgumentException())
        when (val result = remoteDataSource.fetchComments(123)) {
            is Result.Success<*> -> {
                fail()
            }
            is Result.Error<*> -> {
                Assert.assertEquals(result.code, 404)
                verify(apiService, times(1)).fetchComments(123)
            }
        }
    }
}