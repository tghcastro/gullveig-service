package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.tests.unit.domain.Transactions

interface TransactionsRepository {
    fun getById(any: Long): Transactions
    fun saveAndFlush(transaction: Transactions): Transactions
}