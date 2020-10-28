package com.tghcastro.gullveig.transactions.service.domain.models

class Companies(
        var id: Long,
        var name: String,
        var sector: String,
        var tickers: List<String>
)