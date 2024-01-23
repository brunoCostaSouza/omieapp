package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.data.local.OrderRepository
import com.bruno.omieapp.util.mapperToListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetAllOrdersUseCaseImpl : GetOrdersUseCase, KoinComponent {

    private val mOrderRepository: OrderRepository by inject()

    override suspend fun getAllOrders(): Flow<GetOrderStatus> = flow {
        val flow = this
        mOrderRepository.getAllOrder()
            .catch {
                flow.emit(GetOrderStatus.Error("Ops. Erro ao listar as vendas."))
            }
            .collect {
                flow.emit(GetOrderStatus.Success(it.mapperToListModel()))
            }
    }
}