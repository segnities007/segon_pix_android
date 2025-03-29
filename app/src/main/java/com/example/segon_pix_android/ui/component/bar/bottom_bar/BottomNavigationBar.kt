package com.example.segon_pix_android.ui.component.bar.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    selectedItemIndex: Int,
    navigationItems: NavigationItems,
    onClick: (Int) -> Unit,
) {
    NavigationBar {
        navigationItems.labels.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItemIndex,
                onClick = { onClick(index) },
                label = { Text(item) },
                icon = {
                    Icon(
                        painter =
                            painterResource(
                                if (index == selectedItemIndex) {
                                    navigationItems.selectedIcons[index]
                                } else {
                                    navigationItems.unSelectedIcons[index]
                                },
                            ),
                        contentDescription = null,
                    )
                },
            )
        }
    }
}

@Composable
@Preview
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        selectedItemIndex = 0,
        navigationItems = HubNavigationItems,
    ) { }
}
