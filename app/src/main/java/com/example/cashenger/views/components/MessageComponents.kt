package com.example.cashenger.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashenger.R
import com.example.cashenger.domain.chat.models.ReplyMessageModel
import com.example.cashenger.domain.chat.models.SelfMessageModel
import com.example.cashenger.ui.theme.Grey
import com.example.cashenger.ui.theme.Grey2
import com.example.cashenger.ui.theme.LightGrey
import com.example.cashenger.ui.theme.MetallicDarkBlue
import com.example.cashenger.utils.ExpenseCategory

@Composable
fun SelfMessageComponent(
    msgBody: SelfMessageModel = SelfMessageModel()
) {
    
    val resources = LocalContext.current.resources
    val displayMetrics = resources.displayMetrics
    val screenWidthInDp = displayMetrics.widthPixels / displayMetrics.density

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = stringResource(id = R.string.me),
            color = Grey,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(10.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .requiredWidthIn(
                        min = (screenWidthInDp / 4).dp,
                        max = (0.8 * screenWidthInDp).dp,
                    )
                    .background(MetallicDarkBlue, RoundedCornerShape(12.dp, 4.dp, 12.dp, 12.dp))
                    .padding(16.dp, 10.dp),
            ) {
                Text(
                    text = msgBody.msgText,
                    color = Color.White
                )
            }

            if (msgBody.category != ExpenseCategory.Other) {
                Image(
                    painter = painterResource(id = msgBody.category.categoryIconRes ?: 0),
                    contentDescription = "category",
                    modifier = Modifier
                        .padding(10.dp, 4.dp)
                        .size(15.dp, 15.dp)
                )
            }
        }
    }

}

@Composable
fun ReplyMessageComponent(
    msgBody: ReplyMessageModel = ReplyMessageModel()
) {
    val resources = LocalContext.current.resources
    val displayMetrics = resources.displayMetrics
    val screenWidthInDp = displayMetrics.widthPixels / displayMetrics.density

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.casher),
            color = Grey,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(10.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            modifier = Modifier
                .requiredWidthIn(
                    min = (screenWidthInDp / 4).dp,
                    max = (0.8 * screenWidthInDp).dp,
                )
                .background(LightGrey, RoundedCornerShape(4.dp, 12.dp, 12.dp, 12.dp))
                .padding(16.dp, 10.dp),
        ) {
            Text(
                text = msgBody.msgText,
                color = Grey2
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SelfMessageComponentPreview() {
//    SelfMessageComponent()
//    ReplyMessageComponent()
    LazyColumn {
        item {
            SelfMessageComponent()
            ReplyMessageComponent()
        }
    }
}