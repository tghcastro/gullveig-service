package com.tghcastro.gullveig.operations.service.domain.services

import com.tghcastro.gullveig.operations.service.domain.interfaces.TransactionsService
import com.tghcastro.gullveig.operations.service.domain.models.DomainTransactionType
import com.tghcastro.gullveig.operations.service.domain.models.Transactions
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class TransactionsDomainService : TransactionsService {
    override fun createTransaction(transaction: Transactions): Transactions {
        return Transactions(
                "KO",
                units = 10.0,
                type = DomainTransactionType.SELL,
                tags = listOf("SURE PASSSIVE INCOME"),
                price = 50.0,
                currencyCode = "USD",
                costs = 0.51,
                date = Date.from(Instant.now())
        )

    }

    override fun getTransaction(id: Long): Transactions {
        return Transactions(
                "KO",
                units = 10.0,
                type = DomainTransactionType.SELL,
                tags = listOf("SURE PASSSIVE INCOME"),
                price = 50.0,
                currencyCode = "USD",
                costs = 0.51,
                date = Date.from(Instant.now())
        )
    }
}