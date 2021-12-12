package com.example.cashenger.views.features.viewmodels

import androidx.lifecycle.*
import com.example.cashenger.domain.records.models.IncomeExpenseModel
import com.example.cashenger.domain.records.repository.RecordsRepository
import com.example.cashenger.utils.Injector
import com.example.cashenger.views.features.records.RecordsScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class RecordsViewModel(
    private val repo: RecordsRepository
) : ViewModel() {

    private val _transactionsList = MutableLiveData<List<IncomeExpenseModel>>()
    val transactionsList: LiveData<List<IncomeExpenseModel>> get() = _transactionsList

    private val _uiState = MutableLiveData<RecordsScreenState>(RecordsScreenState.FetchingTransactions)
    val uiState: LiveData<RecordsScreenState> get() = _uiState

    fun getTransactionsListForFollowing(type: String) = viewModelScope.launch {
        val transactions = when(type) {
            "all" -> repo.getAllTransactions().reversed()
            "income" -> repo.getAllIncomes().reversed()
            "expense" -> repo.getAllExpenses().reversed()
            else -> listOf()
        }
        _uiState.value = if (transactions.isEmpty()) RecordsScreenState.NoTransactionsFound else RecordsScreenState.ShowTransactions
        _transactionsList.value = transactions
    }

    fun deleteTransaction(transaction: IncomeExpenseModel) = viewModelScope.launch {
        repo.deleteTransactionRecord(transaction)
        updateCurrentList(transaction)
    }

    private fun updateCurrentList(transaction: IncomeExpenseModel) {
        val currentList = _transactionsList.value?.toMutableList() ?: return
        currentList.remove(transaction)
        _transactionsList.value = currentList
        if (currentList.isEmpty()) {
            _uiState.value = RecordsScreenState.NoTransactionsFound
        }
    }

    companion object {
        class RecordViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RecordsViewModel(Injector.getInjector().providesRecordsRepository()) as T
            }
        }
    }
}