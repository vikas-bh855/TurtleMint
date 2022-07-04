package com.turtlemint.issues

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.turtlemint.issues.ui.IssuesList
import com.turtlemint.issues.ui.theme.MyApplicationTheme
import com.turtlemint.issues.ui.viewmodel.ListViewModel
import okhttp3.Cache

class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}