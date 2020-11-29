package com.tghcastro.gullveig.transactions.service.application.controllers.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
class TransactionServiceServerException(message: String?) : RuntimeException(message)
