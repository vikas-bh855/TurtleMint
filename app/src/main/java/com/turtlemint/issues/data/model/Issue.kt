package com.turtlemint.issues.data.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class IssueList : ArrayList<IssueDetails>()

data class IssueDetails(
    val title: String,
    val user: User,
    val updated_at: String,
    val number: Int,
    val body: String
) : java.io.Serializable

data class User(val avatar_url: String, val login: String) : java.io.Serializable

val issueDetails = IssueDetails(
    title = "This is sample title",
    user = User(avatar_url = "dummy", login = "FakeUser"),
    updated_at = "2022-07-03T11:12:17Z", number = 1000,
    body = "This is sample testing body"
)

class IssueDetailsParameter : PreviewParameterProvider<IssueDetails> {
    override val values = listOf(issueDetails).asSequence()
}
