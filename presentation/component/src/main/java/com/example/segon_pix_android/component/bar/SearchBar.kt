package com.example.segon_pix_android.component.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
) {
    val textFieldState = remember { TextFieldState() }

    val padding = WindowInsets.statusBars.asPaddingValues()
    val height = padding.calculateTopPadding()

    Column(verticalArrangement = Arrangement.Top) {
        SearchBar(
            modifier =
                modifier
                    .offset(y = (-height / 2))
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background),
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                    onSearch = { onSearch(textFieldState.text.toString()) },
                    expanded = false,
                    onExpandedChange = { /*nothing*/ },
                    placeholder = { Text("Search") },
                )
            },
            expanded = false,
            onExpandedChange = { /*nothing*/ },
        ) {
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchingBarPreview() {
    SearchBar(
        onSearch = {},
    )
}
