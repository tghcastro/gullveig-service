package com.tghcastro.gullveig.transactions.service.domain.interfaces

import com.tghcastro.gullveig.transactions.service.domain.models.Companies
import com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies.ClientResult
import org.springframework.stereotype.Service

@Service
interface CompaniesServiceClient {
    fun getCompanyByTicker(ticker: String): ClientResult<Companies>
}