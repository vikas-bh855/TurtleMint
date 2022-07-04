package com.turtlemint.issues.data.repo

import com.turtlemint.issues.data.datasource.RemoteDataSource
import com.turtlemint.issues.data.datasource.Result
import com.turtlemint.issues.data.model.commentDetails
import junit.framework.Assert.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DetailRepositoryTest {

    private lateinit var detailRepository: DetailRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        detailRepository = DetailRepository(remoteDataSource)
    }

    @Test
    fun `test issue list, success`() = runTest {
        whenever(remoteDataSource.fetchComments(123)).thenReturn(
            Result.Success(
                listOf(
                    commentDetails,
                    commentDetails,
                    commentDetails
                )
            )
        )
        val result = detailRepository.fetchCommentList(123)
        result.collectLatest {
            when (it) {
                is Result.Success<*> -> {
                    Assert.assertNotNull(it)
                    Assert.assertEquals(
                        it.data,
                        listOf(commentDetails, commentDetails, commentDetails)
                    )
                    verify(remoteDataSource, times(1)).fetchComments(123)
                }
                is Result.Error<*> -> fail()
            }
        }
    }

    @Test
    fun `test issue list, fail`() = runTest {
        whenever(remoteDataSource.fetchComments(123)).thenReturn(
            Result.Error(
                code = 400, data = "Validation Failed"
            )
        )
        val result = detailRepository.fetchCommentList(123)
        result.collectLatest {
            when (it) {
                is Result.Success<*> -> fail()
                is Result.Error<*> -> {
                    Assert.assertNotNull(it)
                    Assert.assertEquals(it.code, 400)
                    verify(remoteDataSource, times(1)).fetchComments(123)
                }
            }
        }
    }
}