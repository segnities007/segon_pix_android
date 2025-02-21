package com.example.segon_pix_android.ui.component.bar.bottom_bar

import com.example.segon_pix_android.R

object HubNavigationItems : NavigationItems{
    override val selectedIcons: List<Int> = listOf(
        R.drawable.baseline_home_24,
        R.drawable.baseline_trending_up_24,
        R.drawable.baseline_image_24,
        R.drawable.baseline_notifications_24,
        R.drawable.baseline_account_circle_24,
    )
    override val unSelectedIcons: List<Int> = listOf(
        R.drawable.outline_home_24,
        R.drawable.outline_trending_up_24,
        R.drawable.outline_image_24,
        R.drawable.outline_notifications_24,
        R.drawable.outline_account_circle_24,
    )
    override val labels: List<String> = listOf(
        "Home",
        "Trend",
        "Post",
        "Notify",
        "Setting",
    )
}