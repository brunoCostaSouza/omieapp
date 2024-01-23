package com.bruno.omieapp.data.local

import com.bruno.omieapp.domain.realmEntity.ItemEntity
import com.bruno.omieapp.domain.realmEntity.OrderEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.isManaged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class OrderRepositoryImpl(
    private val realm: Realm
): OrderRepository {
    override suspend fun save(orderEntity: OrderEntity): Flow<Boolean> = flow {
        realm.query(OrderEntity::class).max("id", Int::class).find().let {
            if(it == null) orderEntity.id = 1 else orderEntity.id = it.plus(1)
        }
        val isManaged = realm.write {
            toFillId(orderEntity)
            this.copyToRealm(orderEntity).isManaged()
        }
        this.emit(isManaged)
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllOrder(): Flow<List<OrderEntity>> =
        realm.query(OrderEntity::class).asFlow().map { it.list }

    override suspend fun getLastNumberOrder(): Flow<String> = flow {
        realm.query(OrderEntity::class).max("id", Int::class).find().let {
            this.emit(it?.plus(1)?.toString() ?: "1")
        }
    }

    override suspend fun getOrderById(id: Int): Flow<OrderEntity?> = flow {
        val result = realm.query(OrderEntity::class, "id == $id").first().find()
        if (result != null) {
            this.emit(result)
        } else {
            this.emit(null)
        }
    }

    private fun toFillId(orderEntity: OrderEntity) {
        var lastId: Int
        realm.query(ItemEntity::class).max("id", Int::class).find().let {
            lastId = it?.plus(1) ?: 1
        }
        orderEntity.itemEntities.forEach {
            it.id = lastId
            lastId = lastId.plus(1)
        }
    }
}