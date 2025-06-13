package com.example.segon_pix_android.feature_auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.feature_auth.sign_in.SignIn
import com.example.segon_pix_android.feature_auth.sign_up.SignUp
import com.example.segon_pix_android.presentation.model.LoginRoute

@Composable
fun Login() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginRoute.SignUp) {
        composable<LoginRoute.SignUp> {
            SignUp()
        }
        composable<LoginRoute.SignIn> {
            SignIn()
        }
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
