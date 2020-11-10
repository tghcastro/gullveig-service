package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies.contracts

class GetCompanyByTicker(
        var id: Long,
        var name: String,
        var sector: String,
        var tickers: List<String>
) {
}