package com.example.segon_pix_android.feature_auth

fun authReducer(
    state: AuthState,
    intent: AuthIntent,
): AuthState =
    when (intent) {
        is AuthIntent.GoToHub -> {
            intent.coreNavHostController.navigate(intent.featureRoute)
            state
        }
        is AuthIntent.SetEmailAndPassword -> state.copy(email = intent.email, password = intent.password)
        is AuthIntent.BackToStart -> state.copy(email = "", password = "", name = "", description = "", code = "", birthday = 20000101)
        else -> state
    }
