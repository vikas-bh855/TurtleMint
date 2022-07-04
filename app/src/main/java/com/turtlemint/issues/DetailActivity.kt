package com.turtlemint.issues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.turtlemint.issues.data.model.IssueDetails
import com.turtlemint.issues.ui.DetailList
import com.turtlemint.issues.ui.theme.MyApplicationTheme
import com.turtlemint.issues.ui.viewmodel.DetailViewModel

class DetailActivity : ComponentActivity() {
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val issueDetails = intent.getSerializableExtra("issue") as IssueDetails
        lifecycleScope.launchWhenCreated {
            viewModel.fetchCommentsList(issueDetails.number)
        }
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    DetailList(commentList = viewModel.detailState.value, issueDetails.title)
                }
            }
        }
    }
}