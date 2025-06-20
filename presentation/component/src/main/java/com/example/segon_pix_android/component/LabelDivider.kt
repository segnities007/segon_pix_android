package com.example.segon_pix_android.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LabelDivider(label: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        ){
        HorizontalDivider(modifier = Modifier.weight(2f))
        Text(modifier = Modifier.padding(8.dp), text = label)
        HorizontalDivider(modifier = Modifier.weight(10f))
    }
}

@Composable
@Preview(showBackground = true)
private fun LabelDividerPreview(){
    Box(modifier = Modifier.fillMaxSize()){
        LabelDivider("aaaaaaaaa")
    }
}
