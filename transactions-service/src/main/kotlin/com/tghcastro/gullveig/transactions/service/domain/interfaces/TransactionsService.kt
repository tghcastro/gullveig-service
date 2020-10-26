package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.domain.models.Transactions

interface TransactionsService {
    fun createTransaction(transaction: Transactions): Transactions
    fun getById(id: Long): Transactions?
}