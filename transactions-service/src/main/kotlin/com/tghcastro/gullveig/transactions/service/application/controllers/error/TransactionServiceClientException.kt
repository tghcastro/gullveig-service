package com.tghcastro.gullveig.transactions.service.application.controllers.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class TransactionServiceClientException(message: String?) : RuntimeException(message)
