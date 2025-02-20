package com.example.segon_pix_android.ui.screen.hub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Hub(
    content: @Composable () -> Unit,
){
    val drawerContent: @Composable () -> Unit = {

    }

    ModalNavigationDrawer(
        drawerContent = drawerContent
    ) {
        HubUi(content = content)
    }
}

@Composable
private fun HubUi(
    content: @Composable () -> Unit,
    ){
    Scaffold {innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            content()
        }
    }
}