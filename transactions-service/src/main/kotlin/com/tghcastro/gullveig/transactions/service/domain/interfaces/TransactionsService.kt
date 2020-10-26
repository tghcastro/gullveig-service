package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.domain.models.Transactions

interface TransactionsService {
    fun createTransaction(operation: Transactions): Transactions
    fun getTransaction(id: Long): Transactions
}