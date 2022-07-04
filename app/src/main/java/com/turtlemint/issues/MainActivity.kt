package com.turtlemint.issues

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import com.turtlemint.issues.ui.IssuesList
import com.turtlemint.issues.ui.theme.MyApplicationTheme
import com.turtlemint.issues.ui.viewmodel.ListViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    IssuesList(issuesList = viewModel.listState.value) {
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("issue", it)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}