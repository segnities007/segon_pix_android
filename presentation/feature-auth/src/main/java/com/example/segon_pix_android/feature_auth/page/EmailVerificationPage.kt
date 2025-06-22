package com.example.segon_pix_android.feature_auth.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.segon_pix_android.feature_auth.AuthIntent

@Composable
fun EmailVerificationPage(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
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
        EnterButton(email, password, pagerState, onLoginIntent)
    }
}

@Composable
private fun Title() {
    Text(
        text = "Input your email \n and \n new password",
        fontSize = 36.sp,
        lineHeight = 60.sp,
        textAlign = TextAlign.Center,
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
            label = { Text(text = "New Password") },
        )
    }
}

@Composable
private fun EnterButton(
    email: String,
    password: String,
    pagerState: PagerState,
    onAuthIntent: (AuthIntent) -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onAuthIntent(AuthIntent.SetEmailAndPassword(email, password, pagerState)) },
    ) {
        Text(text = "Enter")
    }
}

@Composable
@Preview(showBackground = true)
private fun EmailVerificationPagePreview() {
    val pagerState =
        rememberPagerState(pageCount = { 1 })
    EmailVerificationPage(onLoginIntent = {}, pagerState = pagerState)
}
