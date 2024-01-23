package com.bruno.omieapp.arch

import com.bruno.omieapp.domain.realmEntity.ItemEntity
import com.bruno.omieapp.domain.realmEntity.OrderEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object DatabaseModule {
    fun provideRealm(): Realm {
        val config = RealmConfiguration.create(setOf(OrderEntity::class, ItemEntity::class))
        return Realm.open(config)
    }
}