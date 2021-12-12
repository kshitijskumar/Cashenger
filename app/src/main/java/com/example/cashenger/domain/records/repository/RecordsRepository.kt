package com.example.cashenger.domain.records.repository

import com.example.cashenger.domain.db.dao.RecordsDao
import com.example.cashenger.domain.records.models.IncomeExpenseModel
import com.example.cashenger.utils.Injector
import com.example.cashenger.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RecordsRepository(
    private val recordsDao: RecordsDao = Injector.getInjector().providesRecordsDao()
) {

    suspend fun insertOrUpdateAmountDetails(
        title: String,
        amount: Long,
        isExpense: Boolean,
        categoryId: Int = -1,
    ) {
        withContext(Dispatchers.IO) {
            try {
                val amountModel = IncomeExpenseModel(
                    title = title,
                    date = Utils.getCurrentDateTime(),
                    amount = amount,
                    isExpense = isExpense,
                    expenseCategory = categoryId
                )
                recordsDao.insertOrUpdateNewAmountDetails(amountModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getAllExpenses() : List<IncomeExpenseModel> {
        return withContext(Dispatchers.IO) {
            try {
                recordsDao.getAllExpenseRecords()
            } catch (e: Exception) {
                listOf()
            }
        }
    }

    suspend fun getAllIncomes() : List<IncomeExpenseModel> {
        return withContext(Dispatchers.IO) {
            try {
                recordsDao.getAllIncomeRecords()
            } catch (e: Exception) {
                listOf()
            }
        }
    }

    suspend fun getAllTransactions() : List<IncomeExpenseModel> {
        return withContext(Dispatchers.IO) {
            try {
                recordsDao.getAllTransactions()
            } catch (e: Exception) {
                listOf()
            }
        }
    }

    suspend fun deleteTransactionRecord(transaction: IncomeExpenseModel) {
        withContext(Dispatchers.IO) {
            try {
                recordsDao.deleteTransaction(transaction)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}