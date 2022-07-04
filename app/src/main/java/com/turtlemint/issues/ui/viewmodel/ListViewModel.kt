package com.turtlemint.issues.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtlemint.issues.data.model.IssueDetails
import com.turtlemint.issues.data.model.IssueList
import com.turtlemint.issues.data.repo.ListRepository
import com.turtlemint.issues.data.datasource.RemoteDataSource
import com.turtlemint.issues.data.datasource.Result
import com.turtlemint.issues.data.service.Network
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    val listState = mutableStateOf(listOf<IssueDetails>())
    val errorState = mutableStateOf("")
    private val listRepository = ListRepository(RemoteDataSource(Network.getApiService()))

    init {
        viewModelScope.launch {
            fetchIssuesList()
        }
    }

    private suspend fun fetchIssuesList() {
        listRepository.fetchIssueList().collect {
            when (it) {
                is Result.Success<*> -> listState.value = (it.data as IssueList)
                else -> errorState.value = "Unable to fetch issues..."
            }
        }
    }
}