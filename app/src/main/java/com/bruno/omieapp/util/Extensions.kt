package com.bruno.omieapp.util

import io.realm.kotlin.types.RealmInstant
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDateTime.formatData(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    return formatter.format(this)
}

fun RealmInstant.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofEpochSecond(epochSeconds, nanosecondsOfSecond, ZoneOffset.UTC)
}

fun LocalDateTime.toRealmInstant(): RealmInstant =
    RealmInstant.from(this.toEpochSecond(ZoneOffset.UTC), this.nano)

fun Double.formatCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return format.format(this)
}

fun String.formatToDouble(): Double {
    var value = this.replace(".", "")
    value = value.replace(",", ".")
    return value.toDouble()
}