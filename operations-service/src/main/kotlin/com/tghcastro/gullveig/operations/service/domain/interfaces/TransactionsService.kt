package com.tghcastro.gullveig.operations.service.domain.interfaces

import com.tghcastro.gullveig.operations.service.domain.models.Transactions

interface TransactionsService {
    abstract fun createTransaction(operation: Transactions) : Transactions
    abstract fun getTransaction(id: Long): Transactions
}