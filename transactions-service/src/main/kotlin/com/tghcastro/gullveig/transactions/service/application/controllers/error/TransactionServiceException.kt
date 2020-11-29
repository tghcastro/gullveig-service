package com.tghcastro.gullveig.transactions.service.application.controllers.error

import com.tghcastro.gullveig.transactions.service.domain.models.Transactions
import com.tghcastro.gullveig.transactions.service.domain.results.DomainResult
import com.tghcastro.gullveig.transactions.service.domain.results.FailureType

class TransactionServiceException(result: DomainResult<Transactions>) : RuntimeException() {
    init {
        if (result.failureType == FailureType.CLIENT) {
            throw TransactionServiceClientException(result.error())
        } else {
            throw TransactionServiceServerException(result.error())
        }
    }
}
