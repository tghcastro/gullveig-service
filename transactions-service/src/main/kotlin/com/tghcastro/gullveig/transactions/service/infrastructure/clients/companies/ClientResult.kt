package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies

import de.undercouch.gradle.tasks.download.org.apache.http.client.methods.CloseableHttpResponse

class ClientResult<T> {
    private val value: T?
    private val success: Boolean
    private val clientError: ClientError?

    constructor(value: T?, success: Boolean, errorMessage: ClientError?) {
        this.value = value
        this.success = success
        this.clientError = errorMessage
    }

    constructor(value: T) {
        this.value = value
        success = true
        clientError = null
    }

    fun failed(): Boolean {
        return !success
    }

    fun succeeded(): Boolean {
        return success
    }

    fun onSuccess(function: () -> ClientResult<T>): ClientResult<T> {
        return if (succeeded()) {
            return function.invoke()
        } else this
    }

    fun onFailure(function: () -> ClientResult<T>): ClientResult<T> {
        return if (failed()) {
            return function.invoke()
        } else this
    }

    fun onSuccessReturnValue(): T? {
        return if (succeeded()) {
            value
        } else null
    }

    fun error(): ClientError? {
        return clientError
    }

    fun value(): T? {
        return value
    }

    companion object {
        fun <T> success(value: T): ClientResult<T> {
            return ClientResult(value, true, null)
        }

        fun <T> failure(httpResponse: CloseableHttpResponse, message: String): ClientResult<T> {
            val clientError = ClientError()
            clientError.code = httpResponse.statusLine.statusCode
            clientError.message = message
            clientError.rawResponse = httpResponse.toString()
            return ClientResult(null, false, clientError)
        }
    }

    class ClientError {
        var code: Int? = null
        var message: String? = null
        var rawResponse: String? = null
    }
}