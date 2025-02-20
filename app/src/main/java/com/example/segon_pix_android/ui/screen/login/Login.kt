package com.example.segon_pix_android.ui.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Login(
    content: @Composable () -> Unit,
) {
    Scaffold(

    ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            content()
        }
    }
}

