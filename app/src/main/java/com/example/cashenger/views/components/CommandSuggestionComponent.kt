package com.example.cashenger.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashenger.ui.theme.Grey2
import com.example.cashenger.ui.theme.Lightest2Blue
import com.example.cashenger.ui.theme.LightestBlue

@Composable
fun CommandSuggestionComponent(
    suggestingText: String = "suggestions text",
    onSuggestionClick: (suggestion: String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(6.dp, 0.dp)
            .wrapContentSize()
            .background(LightestBlue, RoundedCornerShape(100.dp))
            .clickable { onSuggestionClick.invoke(suggestingText) }
            .border(BorderStroke(1.dp, Lightest2Blue), RoundedCornerShape(100.dp))
            .padding(10.dp, 3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = suggestingText,
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 1.dp)
        )
    }
}

@Preview(showSystemUi = false)
@Composable
fun CommandSuggestionComponentPrev() {
    CommandSuggestionComponent()
}