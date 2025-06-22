package com.example.segon_pix_android.feature_auth.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.feature_auth.AuthIntent

@Composable
fun CreateAccountPage(
    modifier: Modifier = Modifier,
    coreNavHostController: NavHostController,
    onLoginIntent: (AuthIntent) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    val updateName: (String) -> Unit = { name = it }
    val updateCode: (String) -> Unit = { code = it }
    val updateBirthday: (String) -> Unit = { if (it.length <= 8) birthday = it.filter { it.isDigit() } }

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
        Spacer(modifier = Modifier.height(100.dp))
        InputForm(name, code, birthday, updateName, updateCode, updateBirthday)
        Spacer(modifier = Modifier.height(64.dp))
        CreateButton(name, code, birthday, coreNavHostController, onLoginIntent)
    }
}

@Composable
private fun Title() {
    Text(
        text = "Create your account",
        fontSize = 48.sp,
        lineHeight = 60.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun InputForm(
    name: String,
    code: String,
    birthday: String,
    updateNane: (String) -> Unit,
    updateCode: (String) -> Unit,
    updateBirthday: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = updateNane,
            label = { Text(text = "Handle Name") },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = birthday,
            onValueChange = updateBirthday,
            label = { Text(text = "Birthday") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = code,
            onValueChange = updateCode,
            label = { Text(text = "Code") },
        )
    }
}

@Composable
private fun CreateButton(
    name: String,
    code: String,
    birthday: String,
    coreNavHostController: NavHostController,
    onAuthIntent: (AuthIntent) -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onAuthIntent(AuthIntent.CreateAccount(name, code, birthday, coreNavHostController)) },
    ) {
        Text(text = "Create")
    }
}

@Composable
@Preview(showBackground = true)
private fun CreateAccountPagePreview() {
    val nav = rememberNavController()
    CreateAccountPage(onLoginIntent = {}, coreNavHostController = nav)
}
