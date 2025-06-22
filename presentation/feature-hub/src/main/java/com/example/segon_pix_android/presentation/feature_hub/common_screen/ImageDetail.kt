package com.example.segon_pix_android.presentation.feature_hub.common_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.segon_pix_android.component.divider.LabelDivider
import com.example.segon_pix_android.domain.model.PostedImage
import com.example.segon_pix_android.domain.model.User

@Composable
fun ImageDetail(imageId: Long) {
    LaunchedEffect(Unit) {
        // TODO Init
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Details(postedImage = PostedImage(), user = User())
        LabelDivider(label = "#Tag")
        Tags()
        LabelDivider(label = "Comment")
        Comments()
    }
}

@Composable
private fun Details(
    postedImage: PostedImage,
    user: User,
) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier.size(32.dp),
                model = user.icon,
                contentDescription = "User icon",
            )
            Text(text = "@${user.name}")
        }
        AsyncImage(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(256.dp),
            model = postedImage.url,
            contentDescription = "Image",
        )
    }
}

@Composable
private fun Tags() {
    // TODO
}

@Composable
private fun Comments() {
    // TODO
}

@Composable
@Preview(showBackground = true)
private fun ImageDetailPreview() {
    ImageDetail(0)
}
