package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.domain.models.Companies

interface CompaniesServiceClient {
    fun getCompanyByTicker(ticker: String): Companies?
}