package com.example.cashenger.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cashenger.R
import com.example.cashenger.ui.theme.LightGrey


@Composable
fun MessageFieldComponent() {
    
    var textCommand by rememberSaveable {
        mutableStateOf("")
    }
    
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(4.dp, 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = {  }) {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = "add category",
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(LightGrey, CircleShape)
                    .padding(4.dp)
            )
        }

        TextField(
            value = textCommand,
            onValueChange = { textCommand = it },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(30.dp),
            placeholder = { Text(text = stringResource(id = R.string.enter_your_message_here)) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = LightGrey,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )

        if (textCommand.isNotEmpty()) {
            IconButton(onClick = {  }) {
                Image(
                    imageVector = Icons.Default.Send,
                    contentDescription = "send button",
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .background(LightGrey, CircleShape)
                        .padding(10.dp)

                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MessageFieldComponentPreview() {
MessageFieldComponent()
}