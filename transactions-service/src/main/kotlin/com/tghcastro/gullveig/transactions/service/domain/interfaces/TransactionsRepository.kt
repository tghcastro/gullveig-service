package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.domain.models.Transactions

interface TransactionsRepository {
    fun getById(any: Long): Transactions
}