package com.captures2024.soongan.core.data.utils

import com.captures2024.soongan.core.model.exception.NetworkExceptionWrapper
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

@Suppress("TooGenericExceptionCaught")
internal suspend fun <T> safeAPICall(
    request: suspend () -> Response<T>,
): BaseAPIResult<T> = try {
    val response = request()

    when (response.isSuccessful) {
        true -> {
            // Success Response
            BaseAPIResult(
                headers = response.headers(),
                body = response.body(),
            )
        }
        false -> {
            // Fail Response
            val errorBody = response.errorBody()?.string() ?: "UNKNOWN_ERROR"

            val exception = Exception(errorBody)

            throw NetworkExceptionWrapper(
                statusCode = response.code(),
                message = exception.message,
                cause = exception,
            )
        }
    }
} catch (exception: NetworkExceptionWrapper) {
    throw exception
} catch (exception: HttpException) {
    // http exception
    throw NetworkExceptionWrapper(
        statusCode = exception.code(),
        message = exception.message,
        cause = exception,
    )
} catch (exception: UnknownHostException) {
    // UnknownHost exception cause: not connect network
    throw NetworkExceptionWrapper(
        message = exception.message,
        cause = exception,
    )
} catch (exception: Exception) {
    // Unknown exception
    throw NetworkExceptionWrapper(
        message = exception.message,
        cause = exception,
    )
}
