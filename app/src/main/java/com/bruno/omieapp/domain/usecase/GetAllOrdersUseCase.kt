package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow

interface GetOrdersUseCase {
    suspend fun getAllOrders(): Flow<GetOrderStatus>
}

sealed class GetOrderStatus {
    class Success(val orders: List<OrderModel>): GetOrderStatus()
    class Error(val message: String?) : GetOrderStatus()
}