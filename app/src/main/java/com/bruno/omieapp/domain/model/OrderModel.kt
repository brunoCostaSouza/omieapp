package com.bruno.omieapp.domain.model

import java.io.Serializable
import java.time.LocalDateTime

class OrderModel(
    var id: Int? = null,
    var client: String = "",
    var amount: Double = 0.0,
    var items: MutableList<ItemModel> = mutableListOf(),
    var localDateTime: LocalDateTime = LocalDateTime.now()
): Serializable {

    fun calculateAmount() {
        amount = 0.0
        items.forEach {
            amount += it.quantity * it.unityValue
        }
    }
}