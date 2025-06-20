package com.example.segon_pix_android.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val FONT_SIZE = 24
private const val PADDING = 2

@Composable
fun RoundedCornerButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier.padding(PADDING.dp),
        onClick = onClick,
        enabled = enabled,
    ) {
        Text(text = text, fontSize = FONT_SIZE.sp)
    }
}

@Composable
@Preview(showBackground = true)
private fun RoundedCornerButtonPreview() {
    RoundedCornerButton(text = "Rounded Corner Button", onClick = {})
}
