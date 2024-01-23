package com.bruno.omieapp.data.local

import com.bruno.omieapp.domain.realmEntity.OrderEntity
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun save(orderEntity: OrderEntity): Flow<Boolean>
    suspend fun getAllOrder(): Flow<List<OrderEntity>>
    suspend fun getLastNumberOrder(): Flow<String>
    suspend fun getOrderById(id: Int): Flow<OrderEntity?>
}