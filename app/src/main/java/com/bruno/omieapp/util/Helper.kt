package com.bruno.omieapp.util

import com.bruno.omieapp.domain.model.ItemModel
import com.bruno.omieapp.domain.model.OrderModel
import io.realm.kotlin.ext.realmListOf

class Helper {
    fun getListOrdersMock(): List<OrderModel> {
        var itemEntity = ItemModel(description = "Item 01", quantity = 2, unityValue = 10.5, amount = 21.0)
        var orderEntity = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity2 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity3 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity4 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity5 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity6 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity7 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity8 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity9 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        var orderEntity10 = OrderModel(client = "Peterson", amount = 120.0, items = realmListOf(itemEntity))
        return listOf(orderEntity, orderEntity2)
    }

    fun getListItemsMock(): List<ItemModel> {
        return listOf(getItemModel(), getItemModel(), getItemModel(), getItemModel(), getItemModel())
    }

    fun getItemModel(): ItemModel = ItemModel(description = "Sabão em pó 1kg - Omo Multi", quantity = 10, unityValue = 525.5, amount = 5255.0)
}