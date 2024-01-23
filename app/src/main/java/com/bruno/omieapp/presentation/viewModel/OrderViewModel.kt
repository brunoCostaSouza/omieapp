package com.bruno.omieapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno.omieapp.R
import com.bruno.omieapp.domain.model.ItemModel
import com.bruno.omieapp.domain.model.OrderModel
import com.bruno.omieapp.domain.usecase.GetLastNumberOrderUseCase
import com.bruno.omieapp.domain.usecase.SaveOrderStatus
import com.bruno.omieapp.domain.usecase.SaveOrderUseCase
import com.bruno.omieapp.domain.usecase.ValidateItemModelUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OrderViewModel(
    private val saveOrderUseCase: SaveOrderUseCase,
    private val getLastNumberOrderUseCase: GetLastNumberOrderUseCase,
    private val validateItemModelUseCase: ValidateItemModelUseCase
) : ViewModel() {

    private val _state = mutableStateOf(OrderState())
    val state: State<OrderState> = _state

    private val _listItemsState = mutableStateListOf<ItemModel>()
    val listItemsState = _listItemsState

    private val _toastMessage = MutableSharedFlow<Pair<Int?, String?>>()
    val toastMessage = _toastMessage.asSharedFlow()

    init {
        viewModelScope.launch {
            getLastNumberOrderUseCase.getLastNumberOrder().collect {
                _state.value.lastNumber = it
            }
        }
    }

    fun onEvent(event: OrderEvent) {
        viewModelScope.launch {
            when (event) {
                is OrderEvent.Save -> {
                    saveOrderUseCase(event.order).collect {
                        when (it) {
                            is SaveOrderStatus.Success -> {
                                showFeedbackMessage(resId = R.string.text_feedback_order_success)
                            }
                            is SaveOrderStatus.Error -> {
                                showFeedbackMessage(message = it.message)
                            }
                        }
                    }
                }
            }
        }
    }

    fun addItem(item: ItemModel) {
        validateItemModelUseCase(item).let {
            if (it.first) {
                _listItemsState.add(item)
                updateStateOrder()
                showFeedbackMessage(resId = R.string.text_feedback_product_add_success)
            } else {
                showFeedbackMessage(message = it.second)
            }
        }
    }

    private fun updateStateOrder() {
        _state.value.order.apply {
            items = _listItemsState
            calculateAmount()
        }
    }

    fun removeAt(index: Int) {
        _listItemsState.removeAt(index)
        updateStateOrder()
    }

    private fun showFeedbackMessage(resId: Int? = null, message: String? = null) {
        viewModelScope.launch { _toastMessage.emit(Pair(resId, message)) }
    }
}

sealed class OrderEvent {
    class Save(val order: OrderModel) : OrderEvent()
}

data class OrderState(
    var lastNumber: String = "",
    var order: OrderModel = OrderModel()
)