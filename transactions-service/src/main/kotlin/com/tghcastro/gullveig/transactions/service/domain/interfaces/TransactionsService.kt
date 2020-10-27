package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.domain.models.Transactions
import com.tghcastro.gullveig.transactions.service.domain.results.DomainResult

interface TransactionsService {
    fun create(transaction: Transactions): DomainResult<Transactions>
    fun getById(id: Long): Transactions?
}