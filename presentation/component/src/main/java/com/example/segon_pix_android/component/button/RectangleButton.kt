package com.example.segon_pix_android.component.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val PADDING = 2
private const val ROUNDED_CORNER_SHAPE = 4
private const val FONT_SIZE = 24

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE.dp),
        onClick = onClick,
        enabled = enabled,
    ) {
        Text(text = text, fontSize = FONT_SIZE.sp)
    }
}

@Composable
@Preview(showBackground = true)
private fun RectangleButtonPreview() {
    RectangleButton(text = "Rectangle Button", onClick = {})
}
