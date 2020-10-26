package com.tghcastro.gullveig.operations.service.domain.models

import java.util.*

class Transactions(
        var ticker: String,
        var date: Date,
        var costs: Double,
        var currencyCode: String,
        var price: Double,
        var tags: List<String>,
        var type: DomainTransactionType,
        var units: Double
) {
}
