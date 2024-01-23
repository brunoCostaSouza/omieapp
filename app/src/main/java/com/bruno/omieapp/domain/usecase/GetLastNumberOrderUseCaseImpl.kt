package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.data.local.OrderRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetLastNumberOrderUseCaseImpl: GetLastNumberOrderUseCase, KoinComponent {

    private val mOrderRepository: OrderRepository by inject()

    override suspend fun getLastNumberOrder(): Flow<String> = mOrderRepository.getLastNumberOrder()
}