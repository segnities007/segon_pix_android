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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.segon_pix_android.component.divider.LabelDivider
import com.example.segon_pix_android.domain.model.Image

@Composable
fun Home(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        homeViewModel.onIntent(HomeIntent.Init)
    }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NewImages()
    }
}

@Composable
private fun NewImages(images: List<Image> = listOf()) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            LabelDivider(modifier = Modifier.padding(horizontal = 8.dp), label = "New")
        }
        items(
            items = images,
        ) { id ->
            AsyncImage( // TODO Change AsyncImage to AsyncImageCard
                modifier =
                    Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp)),
                model = "https://avatars.githubusercontent.com/u/134184436?v=4",
                contentDescription = "Image $id",
                contentScale = ContentScale.Crop,
            )
        }
        item(span = StaggeredGridItemSpan.FullLine) {
            // TODO create loading indicator and implement logic.
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomePreview() {
    Home()
}
