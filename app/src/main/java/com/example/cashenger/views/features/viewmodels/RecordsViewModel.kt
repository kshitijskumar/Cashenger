package com.example.cashenger.views.features.viewmodels

import androidx.lifecycle.*
import com.example.cashenger.domain.records.models.IncomeExpenseModel
import com.example.cashenger.domain.records.repository.RecordsRepository
import com.example.cashenger.utils.Injector
import kotlinx.coroutines.launch

class RecordsViewModel(
    private val repo: RecordsRepository
) : ViewModel() {

    private val _transactionsList = MutableLiveData<List<IncomeExpenseModel>>()
    val transactionsList: LiveData<List<IncomeExpenseModel>> get() = _transactionsList

    fun getTransactionsListForFollowing(type: String) = viewModelScope.launch {
        _transactionsList.value = when(type) {
            "all" -> repo.getAllTransactions().reversed()
            "income" -> repo.getAllIncomes().reversed()
            "expense" -> repo.getAllExpenses().reversed()
            else -> listOf()
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