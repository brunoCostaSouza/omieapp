package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.data.local.OrderRepository
import com.bruno.omieapp.util.mapperToModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetOrderByIdUseCaseImpl : GetOrderByIdUseCase, KoinComponent {
    private val mOrderRepository: OrderRepository by inject()

    override suspend fun invoke(id: Int): Flow<GetOrderByIdState> = flow {
        mOrderRepository.getOrderById(id).collect {
            if (it != null) {
                this.emit(GetOrderByIdState.Success(it.mapperToModel()))
            } else {
                this.emit(GetOrderByIdState.Error("Ops. Falha ao buscar a venda."))
            }
        }
    }
}