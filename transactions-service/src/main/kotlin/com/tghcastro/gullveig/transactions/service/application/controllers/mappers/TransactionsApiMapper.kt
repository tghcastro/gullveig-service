package com.tghcastro.gullveig.transactions.service.application.controllers.mappers

import com.tghcastro.gullveig.transactions.service.application.controllers.contracts.GetTransactionsResponse
import com.tghcastro.gullveig.transactions.service.application.controllers.contracts.PostTransactionsRequest
import com.tghcastro.gullveig.transactions.service.application.controllers.contracts.PostTransactionsResponse
import com.tghcastro.gullveig.transactions.service.application.controllers.contracts.TransactionType
import com.tghcastro.gullveig.transactions.service.domain.models.DomainTransactionType
import com.tghcastro.gullveig.transactions.service.domain.models.Transactions

class TransactionsApiMapper {
    companion object {
        fun toTransactionModel(transactionRequest: PostTransactionsRequest) : Transactions {
            return Transactions(
                    ticker = transactionRequest.ticker,
                    date = transactionRequest.date,
                    costs = transactionRequest.costs,
                    currencyCode = transactionRequest.currencyCode,
                    price = transactionRequest.price,
                    tags = transactionRequest.tags,
                    type = toDomainTransactionType(transactionRequest.type),
                    units = transactionRequest.units
            )
        }

        fun toPostTransactionResponse(createdTransaction: Transactions): PostTransactionsResponse {
            return PostTransactionsResponse(
                    ticker = createdTransaction.ticker,
                    date = createdTransaction.date,
                    costs = createdTransaction.costs,
                    currencyCode = createdTransaction.currencyCode,
                    price = createdTransaction.price,
                    tags = createdTransaction.tags,
                    type = toTransactionType(createdTransaction.type),
                    units = createdTransaction.units
            )
        }

        fun toGetTransactionResponse(transaction: Transactions): GetTransactionsResponse {
            return GetTransactionsResponse(
                    ticker = transaction.ticker,
                    date = transaction.date,
                    costs = transaction.costs,
                    currencyCode = transaction.currencyCode,
                    price = transaction.price,
                    tags = transaction.tags,
                    type = toTransactionType(transaction.type),
                    units = transaction.units
            )
        }

        fun toDomainTransactionType(type: TransactionType): DomainTransactionType {
            return when(type) {
                TransactionType.BUY -> DomainTransactionType.BUY
                TransactionType.SELL -> DomainTransactionType.SELL
                else -> throw Exception("Unexpected transaction type. $type")
            }
        }

        fun toTransactionType(type: DomainTransactionType): TransactionType {
            return when(type) {
                DomainTransactionType.BUY -> TransactionType.BUY
                DomainTransactionType.SELL -> TransactionType.SELL
                else -> throw Exception("Unexpected domain transaction type. $type")
            }
        }
    }
}
