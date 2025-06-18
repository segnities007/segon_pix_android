package com.example.segon_pix_android.feature_auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.segon_pix_android.feature_auth.page.*

private const val PAGE_COUNT = 4
private const val INITIAL_PAGE = 1

@Composable
fun Auth(coreNavHostController: NavHostController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val pagerState =
        rememberPagerState(
            pageCount = { PAGE_COUNT },
            initialPage = INITIAL_PAGE,
        )

    LaunchedEffect(Unit) {
        authViewModel.onIntent(AuthIntent.Init(coreNavHostController))
    }

    AuthUi(
        pagerState = pagerState,
        onAuthIntent = authViewModel::onIntent,
    ) {
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> SignInPage(onLoginIntent = authViewModel::onIntent, coreNavHostController = coreNavHostController)
                1 -> StartPage(onLoginIntent = authViewModel::onIntent, pagerState = pagerState)
                2 -> EmailVerificationPage(onLoginIntent = authViewModel::onIntent, pagerState = pagerState)
                3 -> CreateAccountPage(onLoginIntent = authViewModel::onIntent, coreNavHostController = coreNavHostController)
            }
        }
    }
}

@Composable
private fun AuthUi(
    pagerState: PagerState,
    onAuthIntent: (AuthIntent) -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            TextButton(
                modifier = Modifier,
                onClick = { onAuthIntent(AuthIntent.BackToStart(pagerState)) },
            ) {
                Text(
                    text = "Back to Start",
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AuthUiPreview() {
    val pagerState =
        rememberPagerState(
            pageCount = { PAGE_COUNT },
            initialPage = INITIAL_PAGE,
        )
    AuthUi(pagerState = pagerState, {}) {
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> {}
                1 -> {}
                2 -> {}
                3 -> {}
            }
        }
    }
}
