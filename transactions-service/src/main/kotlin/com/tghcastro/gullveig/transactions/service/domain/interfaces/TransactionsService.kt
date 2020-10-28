package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.domain.results.DomainResult
import com.tghcastro.gullveig.transactions.service.tests.unit.domain.Transactions

interface TransactionsService {
    fun create(transaction: Transactions): DomainResult<Transactions>
    fun getById(id: Long): Transactions?
}