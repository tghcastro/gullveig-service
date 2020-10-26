package com.tghcastro.gullveig.operations.service.application.controllers.contracts

import java.util.*

class GetTransactionsResponse(
        var ticker: String,
        var date: Date,
        var costs: Double,
        var currencyCode: String,
        var price: Double,
        var tags: List<String>,
        var type: TransactionType,
        var units: Double
) {

}
