package com.example.cashenger.views.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashenger.R
import com.example.cashenger.domain.records.models.IncomeExpenseModel
import com.example.cashenger.ui.theme.DarkGreen
import com.example.cashenger.utils.getExpenseCategoryBasedOnCategoryId

@Composable
fun TransactionItemComponent(
    transaction: IncomeExpenseModel = IncomeExpenseModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(10.dp, 14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            @StringRes
            val stringResource: Int =
                if (transaction.isExpense) R.string.expense else R.string.income
            val color = if (transaction.isExpense) Color.Red else DarkGreen

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = transaction.date,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(id = stringResource),
                        color = color,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    val expenseCategory = getExpenseCategoryBasedOnCategoryId(transaction.expenseCategory)

                    Image(
                        painter = painterResource(
                            id = expenseCategory.categoryIconRes ?: R.drawable.ic_cancel
                        ),
                        contentDescription = "category icon",
                        modifier = Modifier
                            .height(20.dp)
                            .aspectRatio(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = transaction.amount.toString(),
                    fontSize = 24.sp,
                    color = color,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.amount_on),
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(0.dp, 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.amount_cause_highlight, transaction.title),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TransactionItemComponentPreview() {
    TransactionItemComponent()
}