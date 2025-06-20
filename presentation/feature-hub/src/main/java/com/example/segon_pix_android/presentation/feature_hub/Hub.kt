package com.example.segon_pix_android.presentation.feature_hub

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.presentation.feature_hub.home.Home
import com.example.segon_pix_android.presentation.feature_hub.profile.Profile
import com.example.segon_pix_android.presentation.feature_hub.search.Search
import com.example.segon_pix_android.presentation.model.HubRoute

@Composable
fun Hub(coreNavController: NavHostController) {
    val hubNavController = rememberNavController()
    val currentRoute = rememberCurrentHubRoute(hubNavController)

    HubUi(currentRoute, hubNavController) {
        NavHost(navController = hubNavController, startDestination = HubRoute.Home) {
            composable<HubRoute.Home> {
                Home()
            }
            composable<HubRoute.Search> {
                Search()
            }
            composable<HubRoute.Profile> {
                Profile(coreNavController = coreNavController)
            }
        }
    }
}

@Composable
private fun HubUi(
    currentRoute: HubRoute,
    hubNavController: NavController,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = { BottomBar(currentRoute, hubNavController) },
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(innerPadding),
        ) {
            content()
        }
    }
}

@Composable
private fun BottomBar(
    currentRoute: HubRoute,
    hubNavController: NavController,
) {
    val routes = listOf(HubRoute.Home, HubRoute.Search, HubRoute.Profile)

    val unSelectedIcon =
        listOf(
            painterResource(R.drawable.outline_home_24),
            painterResource(R.drawable.outline_search_24),
            painterResource(R.drawable.outline_account_circle_24),
        )

    val selectedIcon =
        listOf(
            painterResource(R.drawable.baseline_home_filled_24),
            painterResource(R.drawable.outline_search_24),
            painterResource(R.drawable.baseline_account_circle_24),
        )

    val iconActions =
        listOf(
            { hubNavController.navigate(HubRoute.Home) },
            { hubNavController.navigate(HubRoute.Search) },
            { hubNavController.navigate(HubRoute.Profile) },
        )

    NavigationBar {
        selectedIcon.forEachIndexed { index, painter ->
            NavigationBarItem(
                selected = currentRoute == routes[index],
                onClick = iconActions[index],
                icon = {
                    Icon(
                        painter = if (currentRoute == routes[index]) painter else unSelectedIcon[index],
                        contentDescription = null,
                    )
                },
            )
        }
    }
}

@Composable
internal fun rememberCurrentHubRoute(navController: NavController): HubRoute {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouteString = navBackStackEntry?.destination?.route

    return when (currentRouteString) {
        HubRoute.Home::class.qualifiedName -> HubRoute.Home
        HubRoute.Search::class.qualifiedName -> HubRoute.Search
        HubRoute.Profile::class.qualifiedName -> HubRoute.Profile
        else -> HubRoute.Home
    }
}
