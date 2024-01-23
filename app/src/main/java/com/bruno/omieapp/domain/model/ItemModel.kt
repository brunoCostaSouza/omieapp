package com.bruno.omieapp.domain.model

import java.io.Serializable

class ItemModel(
    var id: Int? = null,
    var description: String = "",
    var quantity: Int = 0,
    var unityValue: Double = 0.0,
    var amount: Double = 0.0
): Serializable {

    constructor(description: String, quantity: Int, unityValue: Double) : this() {
        this.description = description
        this.quantity = quantity
        this.unityValue = unityValue
        calculateAmount()
    }

    fun calculateAmount() {
        amount = 0.0
        amount = quantity * unityValue
    }
}