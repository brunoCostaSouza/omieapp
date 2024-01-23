package com.bruno.omieapp.domain.usecase

import com.bruno.omieapp.data.local.OrderRepository
import com.bruno.omieapp.domain.model.OrderModel
import com.bruno.omieapp.util.mapperToEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveOrderUserCaseImpl : SaveOrderUseCase, KoinComponent {

    private val mOrderRepository: OrderRepository by inject()

    override suspend fun invoke(model: OrderModel): Flow<SaveOrderStatus> =
        flow {
            var message = ""
            var isValid = true
            if (model.client.isEmpty()) {
                message += "* Informe o nome do cliente\n"
                isValid = false
            }

            if (model.items.isEmpty()) {
                message += "* Adicione um produto"
                isValid = false
            }

            if (isValid) {
                val entity = model.mapperToEntity()
                entity.calculateAmount()
                mOrderRepository.save(entity).collect { isManaged ->
                    if (isManaged) this.emit(SaveOrderStatus.Success) else this.emit(
                        SaveOrderStatus.Error("* Ops. Falha na venda")
                    )
                }
            } else {
                this.emit(SaveOrderStatus.Error(message))
            }
        }.catch { this.emit(SaveOrderStatus.Error("* Ops. Falha na venda")) }.flowOn(Dispatchers.IO)
}