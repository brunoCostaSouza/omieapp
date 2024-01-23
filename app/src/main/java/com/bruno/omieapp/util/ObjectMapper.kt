package com.bruno.omieapp.util

import com.bruno.omieapp.domain.model.ItemModel
import com.bruno.omieapp.domain.model.OrderModel
import com.bruno.omieapp.domain.realmEntity.ItemEntity
import com.bruno.omieapp.domain.realmEntity.OrderEntity
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList


fun OrderEntity.mapperToModel(): OrderModel {
    val orderModel = OrderModel()
    this.let { entity ->
        orderModel.apply {
            id = entity.id
            client = entity.client
            amount = entity.amount
            localDateTime = entity.realmInstant.toLocalDateTime()
            entity.itemEntities.forEach {
                items.add(ItemModel(it.id, it.description, it.quantity, it.unityValue, it.amount))
            }
        }
    }
    return orderModel
}

fun List<OrderEntity>.mapperToListModel(): List<OrderModel> {
    val listModel: MutableList<OrderModel> = mutableListOf()
    this.forEach {
        listModel.add(it.mapperToModel())
    }
    return listModel
}

fun OrderModel.mapperToEntity(): OrderEntity {
    val orderEntity = OrderEntity()
    this.let {
        orderEntity.apply {
            id = it.id
            client = it.client
            amount = it.amount
            realmInstant = it.localDateTime.toRealmInstant()
            it.items.forEach { model ->
                itemEntities.add(model.mapperToEntity())
            }
        }
    }
    return orderEntity
}

fun ItemModel.mapperToEntity(): ItemEntity {
    val itemEntity = ItemEntity()
    this.let {
        itemEntity.apply {
            id = it.id
            description = it.description
            quantity = it.quantity
            unityValue = it.unityValue
            amount = it.amount
        }
    }
    return itemEntity
}

fun List<ItemModel>.mapperToRealmList(): RealmList<ItemEntity> {
    val realmList: RealmList<ItemEntity> = realmListOf()
    this.forEach {
        realmList.add(it.mapperToEntity())
    }
    return realmList
}