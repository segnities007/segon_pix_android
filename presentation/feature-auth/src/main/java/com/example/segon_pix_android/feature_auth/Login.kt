package com.example.segon_pix_android.feature_auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.segon_pix_android.feature_auth.sign.Sign

@Composable
fun Login() {
    LoginUi {
        Sign()
    }
}

@Composable
private fun LoginUi(content: @Composable () -> Unit) {
    Scaffold { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            content()
        }
    }
}
