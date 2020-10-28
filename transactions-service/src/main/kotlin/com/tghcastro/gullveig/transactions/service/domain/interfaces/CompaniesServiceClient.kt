package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.tests.unit.domain.Companies

interface CompaniesServiceClient {
    fun getCompanyByTicker(ticker: String): Companies?
}