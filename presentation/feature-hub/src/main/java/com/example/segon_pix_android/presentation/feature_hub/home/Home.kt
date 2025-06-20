package com.example.segon_pix_android.presentation.feature_hub.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun Home(modifier: Modifier = Modifier) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NewImages()
    }
}

@Composable
private fun NewImages() {
    val a = listOf(0..100)
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
    ) {
        items(a) { index ->

            for (i in 0..100) {
                AsyncImage(
                    model = "https://avatars.githubusercontent.com/u/134184436?v=4",
                    contentDescription = null,
                )
            }
        }
    }
}
