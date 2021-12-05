package com.example.cashenger.views.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashenger.R

@Composable
fun RecordsAppbarComponent(navigatingFor: String) {
    Surface(
        elevation = 4.dp,
        shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.vault_of_x, navigatingFor),
            color = Color.Black,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(20.dp, 8.dp),
            fontWeight = FontWeight.Bold
        )
    }
}