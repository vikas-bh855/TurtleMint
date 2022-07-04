package com.turtlemint.issues.data.repo

import com.turtlemint.issues.data.datasource.RemoteDataSource
import com.turtlemint.issues.data.datasource.Result
import com.turtlemint.issues.data.model.issueDetails
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
class ListRepositoryTest {

    private lateinit var listRepository: ListRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        listRepository = ListRepository(remoteDataSource)
    }

    @Test
    fun `test issue list, success`() = runTest {
        whenever(remoteDataSource.fetchIssues()).thenReturn(
            Result.Success(
                listOf(
                    issueDetails,
                    issueDetails,
                    issueDetails
                )
            )
        )
        val result = listRepository.fetchIssueList()
        result.collectLatest {
            when (it) {
                is Result.Success<*> -> {
                    Assert.assertNotNull(it)
                    Assert.assertEquals(it.data, listOf(issueDetails, issueDetails, issueDetails))
                    verify(remoteDataSource, times(1)).fetchIssues()
                }
                is Result.Error<*> -> fail()
            }
        }
    }

    @Test
    fun `test issue list, fail`() = runTest {
        whenever(remoteDataSource.fetchIssues()).thenReturn(
            Result.Error(
                code = 400, data = "Validation Failed"
            )
        )
        val result = listRepository.fetchIssueList()
        result.collectLatest {
            when (it) {
                is Result.Success<*> -> fail()
                is Result.Error<*> -> {
                    Assert.assertNotNull(it)
                    Assert.assertEquals(it.code, 400)
                    verify(remoteDataSource, times(1)).fetchIssues()
                }
            }
        }
    }
}