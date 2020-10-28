package com.tghcastro.gullveig.transactions.service.tests.unit.domain.services

import com.tghcastro.gullveig.transactions.service.domain.interfaces.CompaniesServiceClient
import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsRepository
import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsService
import com.tghcastro.gullveig.transactions.service.domain.results.DomainResult
import com.tghcastro.gullveig.transactions.service.tests.unit.domain.Transactions
import org.springframework.stereotype.Service

@Service
class TransactionsDomainService(
        val transactionsRepository: TransactionsRepository,
        val companiesServiceClient: CompaniesServiceClient) : TransactionsService {

    override fun create(transaction: Transactions): DomainResult<Transactions> {
        return transaction.validate()
                .onSuccess { verifyIfStockExists(transaction) }
                .onSuccess { internalCreate(transaction) }
    }

    override fun getById(id: Long): Transactions? {
        return transactionsRepository.getById(id)
    }

    private fun internalCreate(transaction: Transactions): DomainResult<Transactions> {
        val createdTransaction = this.transactionsRepository.saveAndFlush(transaction)
        return DomainResult.success(createdTransaction)
    }

    private fun verifyIfStockExists(transaction: Transactions): DomainResult<Transactions> {
        companiesServiceClient.getCompanyByTicker(transaction.ticker)
                ?: return DomainResult.failure(transaction, "Ticker does not exist [${transaction.ticker}]")
        return DomainResult.success(transaction)
    }

}