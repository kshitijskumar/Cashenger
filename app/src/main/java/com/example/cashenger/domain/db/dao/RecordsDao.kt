package com.example.cashenger.domain.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cashenger.domain.records.models.IncomeExpenseModel

@Dao
interface RecordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateNewAmountDetails(item: IncomeExpenseModel)

    @Query("SELECT * FROM income_expense_table WHERE isExpense")
    suspend fun getAllExpenseRecords() : List<IncomeExpenseModel>

    @Query("SELECT * FROM income_expense_table WHERE NOT(isExpense)")
    suspend fun getAllIncomeRecords() : List<IncomeExpenseModel>

    @Query("SELECT * FROM income_expense_table")
    suspend fun getAllTransactions() : List<IncomeExpenseModel>
}