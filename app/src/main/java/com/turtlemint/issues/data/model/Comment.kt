package com.turtlemint.issues.data.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class CommentList : ArrayList<CommentDetails>()

data class CommentDetails(
    val user: User,
    val body: String,
    val updated_at: String
)


val commentDetails = CommentDetails(
    user = User(avatar_url = "dummy", login = "FakeUser"),
    updated_at = "2022-07-03T11:12:17Z", body = "This is sample body for testing"
)

class CommentDetailsParameter : PreviewParameterProvider<CommentDetails> {
    override val values = listOf(commentDetails).asSequence()
}