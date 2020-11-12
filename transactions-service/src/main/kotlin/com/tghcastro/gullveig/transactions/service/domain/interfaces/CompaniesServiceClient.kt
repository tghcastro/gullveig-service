package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.domain.models.Companies
import org.springframework.stereotype.Service

@Service
interface CompaniesServiceClient {
    fun getCompanyByTicker(ticker: String): Companies?
}