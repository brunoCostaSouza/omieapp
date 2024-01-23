package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow

interface SaveOrderUseCase {
    suspend operator fun invoke(model: OrderModel): Flow<SaveOrderStatus>
}

sealed class SaveOrderStatus {
    object Success : SaveOrderStatus()
    class Error(val message: String) : SaveOrderStatus()
}

