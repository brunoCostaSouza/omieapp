package com.bruno.omieapp.domain.realmEntity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ItemEntity (
    @PrimaryKey
    var id: Int? = null,
    var description: String = "",
    var quantity: Int = 0,
    var unityValue: Double = 0.0,
    var amount: Double = 0.0
): RealmObject {
    constructor(): this(id = null, description = "", quantity = 0, unityValue = 0.0, amount = 0.0)
}