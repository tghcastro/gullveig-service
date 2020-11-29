package com.tghcastro.gullveig.transactions.service.domain.results

import com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies.HttpClientResult

class DomainResult<T> {
    var failureType: FailureType = FailureType.UNKNOWN
    private val value: T
    private val success: Boolean
    private val errorMessage: String?

    constructor(value: T, success: Boolean, errorMessage: String?, failureType: FailureType? = null) {
        this.value = value
        this.success = success
        this.errorMessage = errorMessage
        if (failureType != null) {
            this.failureType = failureType
        }
    }

    constructor(value: T) {
        this.value = value
        success = true
        errorMessage = null
    }

    fun failed(): Boolean {
        return !success
    }

    fun succeeded(): Boolean {
        return success
    }

    fun onSuccess(function: () -> DomainResult<T>): DomainResult<T> {
        return if (succeeded()) {
            return function.invoke()
        } else this
    }

    fun onFailure(function: () -> DomainResult<T>): DomainResult<T> {
        return if (failed()) {
            return function.invoke()
        } else this
    }

    fun onSuccessReturnValue(): T? {
        return if (succeeded()) {
            value
        } else null
    }

    fun error(): String? {
        return errorMessage
    }

    fun value(): T {
        return value
    }

    companion object {
        fun <T> success(value: T): DomainResult<T> {
            return DomainResult(value, true, null)
        }

        fun <T> failure(value: T, errorMessage: String, failureType: FailureType): DomainResult<T> {
            return DomainResult(value, false, errorMessage, failureType)
        }

        // TODO: Remove this reference (HttpClientResult)
        fun <T> failure(value: T, httpClientError: HttpClientResult.ClientError?): DomainResult<T> {
            var errorMessage = "Unknown error"
            var failureType = FailureType.UNKNOWN
            if (httpClientError != null) {
                errorMessage = "${httpClientError.message}[ErrorCode: ${httpClientError.code}][FailureType: ${httpClientError.type}]"
                if (httpClientError.type == HttpClientResult.ClientErrorType.SERVER) {
                    failureType = FailureType.SERVER
                } else {
                    failureType = FailureType.CLIENT
                }
            }

            return DomainResult(value, false, errorMessage, failureType)
        }
    }
}