package com.example.segon_pix_android.component.indicator

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleIndicator(
    loading: Boolean,
    onAction: () -> Unit,
) {
    if (loading) return

    LaunchedEffect(Unit) {
        onAction()
    }

    CircularProgressIndicator(
        modifier = Modifier.size(32.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
@Preview(showBackground = true)
private fun CircuitIndicatorPreview(){
    CircleIndicator(
        loading = true,
        onAction = {},
    )
}