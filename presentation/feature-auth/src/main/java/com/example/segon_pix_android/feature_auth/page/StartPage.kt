package com.example.segon_pix_android.feature_auth.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.segon_pix_android.feature_auth.AuthIntent

@Composable
fun StartPage(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onLoginIntent: (AuthIntent) -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OnionIcon()
        Spacer(modifier = Modifier.height(16.dp))
        Title()
        Spacer(modifier = Modifier.height(16.dp))
        SignButton(pagerState, onLoginIntent)
    }
}

@Composable
private fun OnionIcon() {
    AsyncImage(
        model = "https://avatars.githubusercontent.com/u/134184436?v=4",
        contentDescription = "onion0904",
    )
}

@Composable
private fun Title() {
    Text(
        text = "aaaaa",
        fontSize = 48.sp,
    )
}

@Composable
private fun SignButton(
    pagerState: PagerState,
    onLoginIntent: (AuthIntent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onLoginIntent(AuthIntent.GoToNextPage(2, pagerState)) },
        ) {
            Text(text = "Sign up")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onLoginIntent(AuthIntent.GoToNextPage(0, pagerState)) },
        ) {
            Text(text = "Sign in")
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun StartPagePreview() {
    val pagerState =
        rememberPagerState(pageCount = { 1 })
    StartPage(
        pagerState = pagerState,
        onLoginIntent = {},
    )
}
