package com.example.segon_pix_android.ui.screen.hub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.segon_pix_android.ui.component.bar.bottom_bar.BottomNavigationBar
import com.example.segon_pix_android.ui.component.bar.bottom_bar.BottomNavigationBarAction
import com.example.segon_pix_android.ui.component.bar.bottom_bar.HubNavigationItems
import com.example.segon_pix_android.ui.component.bar.top_bar.TopBarWithMenu

@Composable
fun Hub(
    selectedIndex: Int,
    bottomNavigationBarAction: BottomNavigationBarAction,
    content: @Composable () -> Unit,
) {
    val drawerContent: @Composable () -> Unit = {
    }

    ModalNavigationDrawer(
        drawerContent = drawerContent,
    ) {
        HubUi(
            selectedIndex = selectedIndex,
            bottomNavigationBarAction = bottomNavigationBarAction,
            content = content,
        )
    }
}

@Composable
private fun HubUi(
    selectedIndex: Int,
    bottomNavigationBarAction: BottomNavigationBarAction,
    content: @Composable () -> Unit,
) {
    val topBar: @Composable () -> Unit = {
        TopBarWithMenu {
            Text("Hub")
        }
    }

    val bottomBar: @Composable () -> Unit = {
        BottomNavigationBar(
            selectedItemIndex = selectedIndex,
            navigationItems = HubNavigationItems,
            onClick = bottomNavigationBarAction.onUpdateIndex,
        )
    }

    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

@Composable
@Preview
private fun HubPreview() {
    Hub(
        selectedIndex = 0,
        bottomNavigationBarAction = BottomNavigationBarAction { },
        content = {},
    )
}
