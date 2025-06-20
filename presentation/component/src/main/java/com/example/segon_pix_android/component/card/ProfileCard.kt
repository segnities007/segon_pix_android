package com.example.segon_pix_android.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.segon_pix_android.domain.model.User

@Composable
fun ProfileCard(user: User) {
    ElevatedCard {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            AsyncImage(
                modifier =
                    Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                model = user.icon,
                contentDescription = "Profile picture",
            )
            UserDescription(user = user)
        }
    }
}

@Composable
private fun UserDescription(user: User) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = "名前: ${user.name}")
        Text(text = "生年月日: ${user.birthday}")
    }
}

@Composable
@Preview(showBackground = true)
private fun ProfileCardPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ProfileCard(
            user = User(),
        )
    }
}
