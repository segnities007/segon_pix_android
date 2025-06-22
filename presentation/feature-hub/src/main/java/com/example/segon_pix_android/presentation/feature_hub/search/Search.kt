package com.example.segon_pix_android.presentation.feature_hub.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.segon_pix_android.component.bar.SearchBar
import com.example.segon_pix_android.component.divider.LabelDivider
import com.example.segon_pix_android.component.indicator.CircleIndicator

@Composable
fun Search(modifier: Modifier = Modifier) {
    val searchViewModel: SearchViewModel = hiltViewModel()
    val state = searchViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        searchViewModel.onIntent(SearchIntent.Init)
    }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        SearchBar(onSearch = {
            searchViewModel.onIntent(SearchIntent.Search(it))
            searchViewModel.onIntent(SearchIntent.SetQuery(it))
        })
        LabelDivider(label = "Images")
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            modifier =
                Modifier
                    .padding(top = 16.dp)
                    .height(400.dp),
        ) {
            items(
                state.value.images.size,
                key = { state.value.images[it].id },
            ) { index ->
                AsyncImage(
                    model = state.value.images[index].url,
                    contentDescription = null,
                    modifier =
                        Modifier
                            .padding(4.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(8.dp)),
                )
            }
            item {
                if (state.value.images.isNotEmpty()) {
                    CircleIndicator(
                        loading = state.value.isFetchCompleted,
                        onAction = { searchViewModel.onIntent(SearchIntent.GetMoreImages) },
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchPreview() {
    Search()
}
