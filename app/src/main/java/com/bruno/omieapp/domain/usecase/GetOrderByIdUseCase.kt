package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow

interface GetOrderByIdUseCase {
    suspend operator fun invoke(id: Int): Flow<GetOrderByIdState>
}

sealed class GetOrderByIdState {
    class Success(val orderModel: OrderModel): GetOrderByIdState()
    class Error(val message: String): GetOrderByIdState()
}