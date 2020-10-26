package com.tghcastro.gullveig.transactions.service.application.controllers.contracts

import java.util.*

data class PostTransactionsRequest(
        val ticker: String,
        val type: TransactionType,
        val date: Date,
        val price: Double,
        val units: Double,
        val costs: Double,
        val currencyCode: String,
        val tags: List<String>
)