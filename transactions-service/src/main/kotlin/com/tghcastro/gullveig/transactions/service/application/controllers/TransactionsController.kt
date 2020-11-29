package com.tghcastro.gullveig.transactions.service.application.controllers

import com.tghcastro.gullveig.transactions.service.application.controllers.contracts.GetTransactionsResponse
import com.tghcastro.gullveig.transactions.service.application.controllers.contracts.PostTransactionsRequest
import com.tghcastro.gullveig.transactions.service.application.controllers.contracts.PostTransactionsResponse
import com.tghcastro.gullveig.transactions.service.application.controllers.error.TransactionServiceException
import com.tghcastro.gullveig.transactions.service.application.controllers.mappers.TransactionsApiMapper
import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/transactions")
class TransactionsController(
        @Autowired private val transactionsService: TransactionsService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOperation(@Valid @RequestBody requestBody: PostTransactionsRequest): PostTransactionsResponse {
        val result = this.transactionsService.create(TransactionsApiMapper.toTransactionModel(requestBody))
        if (result.failed()) {
            throw TransactionServiceException(result)
        }
        return TransactionsApiMapper.toPostTransactionResponse(result.value())
    }

    @GetMapping
    @RequestMapping("{id}")
    fun getOperation(@PathVariable id: Long): GetTransactionsResponse {
        val gottenTransaction = this.transactionsService.getById(id) ?: throw Exception("not found")
        return TransactionsApiMapper.toGetTransactionResponse(gottenTransaction)
    }
}