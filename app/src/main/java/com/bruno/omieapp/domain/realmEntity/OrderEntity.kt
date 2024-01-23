package com.bruno.omieapp.domain.realmEntity

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.io.Serializable

class OrderEntity(
    @PrimaryKey
    var id: Int? = null,
    var client: String = "",
    var amount: Double = 0.0,
    var itemEntities: RealmList<ItemEntity> = realmListOf(),
    var realmInstant: RealmInstant = RealmInstant.now()
) : RealmObject, Serializable {
    constructor() : this(id = null, amount = 0.0, itemEntities = realmListOf())

    fun calculateAmount() {
        amount = 0.0
        itemEntities.forEach {
            amount += it.amount
        }
    }
}