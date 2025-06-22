package com.example.segon_pix_android.presentation.feature_hub.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.segon_pix_android.component.card.PostCard
import com.example.segon_pix_android.component.divider.LabelDivider
import com.example.segon_pix_android.component.indicator.CircleIndicator
import com.example.segon_pix_android.domain.model.Image

@Composable
fun Home(
    modifier: Modifier = Modifier,
    images: List<Image>,
    isFetchCompleted: Boolean,
    isFabShow: Boolean,
    fabAction: () -> Unit,
    onHomeIntent: (HomeIntent) -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LabelDivider(modifier = Modifier.padding(horizontal = 8.dp), label = "New")
        NewImages(images, isFetchCompleted, onHomeIntent)
    }

    if (isFabShow) {
        PostCard(
            onPost = { uri, title, hashtag, onDismiss -> onHomeIntent(HomeIntent.PostImage(uri, title, hashtag, onDismiss)) },
            onDismiss = { fabAction() },
        )
    }
}

@Composable
private fun NewImages(
    images: List<Image>,
    isFetchCompleted: Boolean,
    onHomeIntent: (HomeIntent) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
    ) {
        items(
            items = images,
            key = { it.id },
        ) { image ->
            AsyncImage(
                modifier =
                    Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp)),
                model = image.url,
                contentDescription = "Image ${image.id}",
                contentScale = ContentScale.Crop,
            )
        }
        item(span = StaggeredGridItemSpan.FullLine) {
            if(images.isNotEmpty()){
                CircleIndicator(isFetchCompleted) {
                    onHomeIntent(HomeIntent.GetMoreNewImage(
                        last = images.last().id,
                    ))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomePreview() {
    Home(
        isFabShow = false,
        fabAction = {},
        onHomeIntent = {},
        images = listOf(),
        isFetchCompleted = true,
    )
}
