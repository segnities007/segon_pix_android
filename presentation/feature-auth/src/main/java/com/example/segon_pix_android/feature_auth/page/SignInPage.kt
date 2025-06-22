package com.example.segon_pix_android.feature_auth.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.feature_auth.AuthIntent

@Composable
fun SignInPage(
    modifier: Modifier = Modifier,
    coreNavHostController: NavHostController,
    onLoginIntent: (AuthIntent) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val updateEmail: (String) -> Unit = { email = it }
    val updatePassword: (String) -> Unit = { password = it }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(56.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Title()
        Spacer(modifier = Modifier.height(128.dp))
        InputForm(email, password, updateEmail, updatePassword)
        Spacer(modifier = Modifier.height(64.dp))
        CreateButton(email, password, coreNavHostController, onLoginIntent)
    }
}

@Composable
private fun Title() {
    Text(
        text = "Sign in",
        lineHeight = 60.sp,
        fontSize = 48.sp,
    )
}

@Composable
private fun InputForm(
    email: String,
    password: String,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = updateEmail,
            label = { Text(text = "Email") },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = updatePassword,
            label = { Text(text = "Password") },
        )
    }
}

@Composable
private fun CreateButton(
    email: String,
    password: String,
    coreNavHostController: NavHostController,
    onAuthIntent: (AuthIntent) -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onAuthIntent(AuthIntent.SignIn(email, password, coreNavHostController)) },
    ) {
        Text(text = "Enter")
    }
}

@Composable
@Preview(showBackground = true)
private fun SignInPagePreview() {
    val nav = rememberNavController()
    SignInPage(onLoginIntent = {}, coreNavHostController = nav)
}
