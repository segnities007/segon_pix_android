package com.example.segon_pix_android.component.card

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.segon_pix_android.component.button.RectangleButton

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    onPost: (uri: Uri, title: String, hashtag: String) -> Unit,
    onDismiss: () -> Unit,
) {
    var uri by remember { mutableStateOf<Uri?>(null) }
    val pickMedia =
        rememberLauncherForActivityResult(PickVisualMedia()) {
            if (it != null) {
                uri = it
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
    var title by remember { mutableStateOf("") }
    var hashtag by remember { mutableStateOf("") }
    val updateTitle: (String) -> Unit = { title = it }
    val updateHashtag: (String) -> Unit = { hashtag = it }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
                .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(enabled = false, onClick = {})
                    .background(MaterialTheme.colorScheme.background)
                    .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImageCard(uri = uri, onClick = { pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) })
            InputForm(title, hashtag, updateTitle, updateHashtag)
            Buttons(uri, title, hashtag, onPost, onDismiss)
        }
    }
}

@Composable
private fun ImageCard(
    modifier: Modifier = Modifier,
    uri: Uri?,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier =
            modifier
                .height(200.dp)
                .width(300.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if (uri != null) {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(context)
                        .data(uri)
                        .build(),
                contentDescription = "Selected Image",
            )
        } else {
            Text(text = "Touch", fontSize = MaterialTheme.typography.titleLarge.fontSize)
        }
    }
}

@Composable
private fun InputForm(
    title: String,
    hashtag: String,
    updateTitle: (String) -> Unit,
    updateHashtag: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.width(300.dp),
            value = title,
            onValueChange = updateTitle,
            label = { Text(text = "Title") },
        )
        OutlinedTextField(
            modifier = Modifier.width(300.dp),
            value = hashtag,
            onValueChange = updateHashtag,
            label = { Text(text = "Hashtag") },
        )
    }
}

@Composable
private fun Buttons(
    uri: Uri?,
    title: String,
    hashtag: String,
    onPost: (Uri, String, String) -> Unit,
    onDismiss: () -> Unit,
) {
    Row(
        modifier = Modifier.width(300.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RectangleButton(
            modifier = Modifier.weight(1f),
            text = "Cancel",
            onClick = onDismiss,
        )
        RectangleButton(
            modifier = Modifier.weight(1f),
            text = "Post",
            onClick = { if (uri != null) onPost(uri, title, hashtag) },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PostCardPreview() {
    PostCard(
        onPost = { _, _, _ -> },
        onDismiss = {},
    )
}
