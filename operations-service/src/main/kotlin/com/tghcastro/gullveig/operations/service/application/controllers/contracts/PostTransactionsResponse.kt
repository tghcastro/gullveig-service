package com.tghcastro.gullveig.operations.service.application.controllers.contracts

import java.util.*

class PostTransactionsResponse(
        val ticker: String,
        val type: TransactionType,
        val date: Date,
        val price: Double,
        val units: Double,
        val costs: Double,
        val currencyCode: String,
        val tags: List<String>
) {
}