package com.bruno.omieapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno.omieapp.domain.model.OrderModel
import com.bruno.omieapp.domain.usecase.GetOrderByIdState
import com.bruno.omieapp.domain.usecase.GetOrderByIdUseCase
import com.bruno.omieapp.domain.usecase.GetOrderStatus
import com.bruno.omieapp.domain.usecase.GetOrdersUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    val showDialog = mutableStateOf(false)

    private val _goToMainScreenLiveDate = MutableLiveData<Any>()
    val goToMainScreenLiveDate: LiveData<Any>
        get() = _goToMainScreenLiveDate

    private val _goToOrderFormLiveDate = MutableLiveData<Any>()
    val goToOrderFormLiveDate: LiveData<Any>
        get() = _goToOrderFormLiveDate

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.NewOrder -> goToOrderForm()
        }
    }

    fun getAllOrders() {
        viewModelScope.launch {
            getOrdersUseCase.getAllOrders().collect {
                when (it) {
                    is GetOrderStatus.Success -> {
                        _state.value.listOrders = it.orders
                        calculateSalesAmount()
                        _goToMainScreenLiveDate.value = Any()
                    }

                    else -> {}
                }
            }
        }
    }

    fun onItemListClick(id: Int) {
        viewModelScope.launch {
            getOrderByIdUseCase(id).collect {
                when(it) {
                    is GetOrderByIdState.Success -> {
                        _state.value.orderSelected = it.orderModel
                        showDialog.value = true
                    }
                    is GetOrderByIdState.Error -> {}
                }
            }
        }
    }

    private fun calculateSalesAmount() {
        state.value.salesAmount = 0.0
        state.value.listOrders.forEach {
            state.value.salesAmount += it.amount
        }
    }

    private fun goToOrderForm() {
        _goToOrderFormLiveDate.postValue(Any())
    }
}

sealed class MainEvent {
    object NewOrder : MainEvent()
}

data class MainState(
    var salesAmount: Double = 0.0,
    var listOrders: List<OrderModel> = emptyList(),
    var orderSelected: OrderModel? = null
)

