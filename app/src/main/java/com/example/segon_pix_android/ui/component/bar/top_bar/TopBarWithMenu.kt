package com.example.segon_pix_android.ui.component.bar.top_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.segon_pix_android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithMenu(
    centerContent: @Composable () -> Unit,
){
    val commonPadding: Dp = dimensionResource(R.dimen.normal_padding)

    val leftContent: @Composable () -> Unit = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ){
            Spacer(Modifier.padding(commonPadding))
            Icon(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.nl_icon_size))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.small_padding)))
                    .clickable {
                    //TODO
                },
                painter = painterResource(R.drawable.baseline_menu_24),
                contentDescription = null,
            )
        }
    }

    TopBar(
        leftContent = leftContent,
        centerContent = centerContent,
    )
}