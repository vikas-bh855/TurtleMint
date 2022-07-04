package com.turtlemint.issues.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.turtlemint.issues.data.model.IssueDetails
import com.turtlemint.issues.data.model.IssueDetailsParameter
import com.turtlemint.issues.formatDate
import com.turtlemint.issues.ui.theme.borderStrokeColor
import com.turtlemint.issues.ui.theme.cardBackground

@Composable
fun IssuesList(issuesList: List<IssueDetails>, onClick: (IssueDetails) -> Unit) {
    LazyColumn(modifier = Modifier
        .fillMaxSize(), content = {
        items(issuesList) { item ->
            IssueCard(issueDetails = item, onClick)
        }
    })
}

@Preview
@Composable
fun IssueCard(
    @PreviewParameter(IssueDetailsParameter::class) issueDetails: IssueDetails,
    onClick: (IssueDetails) -> Unit = {}
) {
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onClick.invoke(issueDetails)
            },
        backgroundColor = cardBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberAsyncImagePainter(model = issueDetails.user.avatar_url),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentDescription = "User Profile Image"
                )
                Text(
                    text = issueDetails.user.login, fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = issueDetails.updated_at.formatDate(),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(text = "Title:", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = issueDetails.title,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                val body = if (issueDetails.body.length > 200)
                    issueDetails.body.substring(0..199)
                else issueDetails.body
                Text(text = "Desc:", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = body,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 10.dp),

                    )
            }
        }
    }
}