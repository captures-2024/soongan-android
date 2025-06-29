@file:Suppress("LEAKED_IN_PLACE_LAMBDA", "WRONG_INVOCATION_KIND")

package com.captures2024.soongan.domain.usecase.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalContracts::class)
inline fun <T> runSuspendCatching(block: () -> T): Result<T> {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return runCatching(block).also { result ->
        val exception = result.exceptionOrNull()
        if (exception is CancellationException) throw exception
    }
}
