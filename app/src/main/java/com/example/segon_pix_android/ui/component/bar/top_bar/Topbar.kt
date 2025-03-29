package com.example.segon_pix_android.ui.component.bar.top_bar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    leftContent: @Composable () -> Unit = {},
    rightContent: @Composable () -> Unit = {},
    centerContent: @Composable () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = centerContent,
        modifier = modifier,
        navigationIcon = leftContent,
        actions = { rightContent() },
        scrollBehavior = scrollBehavior,
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun TopBarPreview() {
    TopBar {
        Text("a")
    }
}
