package com.example.cashenger.views.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cashenger.R
import com.example.cashenger.domain.chat.models.SelfMessageModel
import com.example.cashenger.ui.theme.LightGrey
import com.example.cashenger.utils.ExpenseCategory
import com.example.cashenger.utils.FeaturesResources


@Composable
fun MessageFieldComponent(
    onSendClick: (SelfMessageModel) -> Unit = {}
) {

    var textCommand by rememberSaveable {
        mutableStateOf("")
    }
    var currSelectedCategory by remember {
        mutableStateOf<ExpenseCategory>(value = ExpenseCategory.Other)
    }

    var areCategoriesVisible by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            IconButton(onClick = { areCategoriesVisible = !areCategoriesVisible }) {
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
                IconButton(onClick = {
                    val msgBody = SelfMessageModel(
                        msgText = textCommand,
                        category = currSelectedCategory
                    )
                    onSendClick.invoke(msgBody)
                    //reset
                    textCommand = ""
                    currSelectedCategory = ExpenseCategory.Other
                    areCategoriesVisible = false
                }) {
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
        if (areCategoriesVisible) {
            AddCategoryComponent(selectedCategory = currSelectedCategory) {
                currSelectedCategory = it
            }
        }
    }
}

@Composable
fun AddCategoryComponent(
    selectedCategory: ExpenseCategory = ExpenseCategory.Other,
    categoryClick: (ExpenseCategory) -> Unit = {}
) {
    LazyRow(
        contentPadding = PaddingValues(4.dp, 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(FeaturesResources.supportedCategoriesList) { category ->
            val categoryBackground =
                if (selectedCategory == category) category.bgColor else LightGrey
            IconButton(
                onClick = { categoryClick.invoke(category) },
                modifier = Modifier
                    .padding(4.dp)
                    .background(categoryBackground, CircleShape)
            ) {
                Image(
                    painter = painterResource(id = category.categoryIconRes ?: 0),
                    contentDescription = "category_${category.categoryId}",
                    modifier = Modifier
                        .size(28.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MessageFieldComponentPreview() {
    MessageFieldComponent()
//    AddCategoryComponent()
}