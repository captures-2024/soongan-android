package com.captures2024.soongan.core.common.extension

import android.os.Bundle

fun Bundle.toMap(): Map<String, Any>? {
    val map: MutableMap<String, Any> = HashMap()
    val ks = keySet()
    val iterator: Iterator<String> = ks.iterator()
    while (iterator.hasNext()) {
        val key = iterator.next()

        @Suppress("DEPRECATION")
        val value = get(key)
        if (value != null)
            map[key] = value
    }
    return map
}
