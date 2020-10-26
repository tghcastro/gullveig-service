package com.tghcastro.gullveig.transactions.service.domain.services

import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsRepository
import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsService
import com.tghcastro.gullveig.transactions.service.domain.models.DomainTransactionType
import com.tghcastro.gullveig.transactions.service.domain.models.Transactions
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class TransactionsDomainService(val transactionsRepository: TransactionsRepository) : TransactionsService {

    override fun createTransaction(transaction: Transactions): Transactions {
        return Transactions(
                id = 1L,
                ticker = "KO",
                units = 10.0,
                type = DomainTransactionType.SELL,
                tags = listOf("SURE PASSSIVE INCOME"),
                price = 50.0,
                currencyCode = "USD",
                costs = 0.51,
                date = Date.from(Instant.now())
        )

    }

    override fun getById(id: Long): Transactions? {
        return transactionsRepository.getById(id)
    }
}