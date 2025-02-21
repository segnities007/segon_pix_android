package com.example.segon_pix_android.ui.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.segon_pix_android.ui.component.bar.top_bar.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    content: @Composable () -> Unit,
) {
    val topBar: @Composable () -> Unit = {
        TopBar {
            Text("Login")
        }
    }

    Scaffold(
        topBar = topBar
    ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            content()
        }
    }
}

@Composable
@Preview
private fun LoginPreview(){
    Login{}
}
