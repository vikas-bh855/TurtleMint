package com.turtlemint.issues.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.turtlemint.issues.data.model.CommentDetails
import com.turtlemint.issues.data.model.IssueDetailsParameter

@Composable
fun DetailList(commentList: List<CommentDetails>, title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp)
        )
        if (commentList.isEmpty())
            Text(
                text = "No comments available...", modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                color = Color.Gray
            )
        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            items(commentList) { item ->
                DetailCard(commentDetails = item)
            }
        })
    }
}

@Preview
@Composable
fun DetailCard(
    @PreviewParameter(IssueDetailsParameter::class) commentDetails: CommentDetails
) {
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        backgroundColor = Color(0xFFECE9EA),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column {
                Row {
                    Image(
                        painter = rememberAsyncImagePainter(model = commentDetails.user.avatar_url),
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentDescription = "User Profile Image"
                    )
                    Text(
                        text = commentDetails.user.login,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Text(text = commentDetails.body, modifier = Modifier.padding(top = 10.dp))
            }
        }
    }
}