package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies

import de.undercouch.gradle.tasks.download.org.apache.http.client.methods.CloseableHttpResponse

class HttpClientResult<T> {
    private val value: T?
    private val success: Boolean
    private val clientError: ClientError?

    constructor(value: T?, success: Boolean, clientError: ClientError?) {
        this.value = value
        this.success = success
        this.clientError = clientError
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

    fun onSuccess(function: () -> HttpClientResult<T>): HttpClientResult<T> {
        return if (succeeded()) {
            return function.invoke()
        } else this
    }

    fun onFailure(function: () -> HttpClientResult<T>): HttpClientResult<T> {
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
        fun <T> success(value: T): HttpClientResult<T> {
            return HttpClientResult(value, true, null)
        }

        fun <T> failure(httpResponse: CloseableHttpResponse, message: String, errorType: ClientErrorType): HttpClientResult<T> {
            val clientError = ClientError()
            clientError.code = httpResponse.statusLine.statusCode
            clientError.message = message
            clientError.rawResponse = httpResponse.toString()
            clientError.type = errorType
            return HttpClientResult(null, false, clientError)
        }
    }

    class ClientError {
        var type: ClientErrorType = ClientErrorType.SERVER
        var code: Int? = null
        var message: String? = null
        var rawResponse: String? = null
    }

    enum class ClientErrorType {
        CLIENT, SERVER
    }
}