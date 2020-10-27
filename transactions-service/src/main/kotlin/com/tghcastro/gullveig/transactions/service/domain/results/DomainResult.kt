package com.tghcastro.gullveig.transactions.service.domain.results

class DomainResult<T> {
    private val value: T
    private val success: Boolean
    private val errorMessage: String?

    constructor(value: T, success: Boolean, errorMessage: String?) {
        this.value = value
        this.success = success
        this.errorMessage = errorMessage
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

        fun <T> failure(value: T, errorMessage: String): DomainResult<T> {
            return DomainResult(value, false, errorMessage)
        }
    }
}