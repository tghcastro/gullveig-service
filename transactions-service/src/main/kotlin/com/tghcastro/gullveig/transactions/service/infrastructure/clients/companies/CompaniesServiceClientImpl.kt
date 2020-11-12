package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies

import com.tghcastro.gullveig.transactions.service.domain.interfaces.CompaniesServiceClient
import com.tghcastro.gullveig.transactions.service.domain.models.Companies
import org.springframework.stereotype.Service

@Service
class CompaniesServiceClientImpl(val httpClient: CompaniesServiceHttpClient) : CompaniesServiceClient {
    override fun getCompanyByTicker(ticker: String): Companies? {
        val getCompanyByTickerResponse = httpClient.getCompanyByTicker(ticker)

        if (getCompanyByTickerResponse != null) {
            return Companies(
                    id = getCompanyByTickerResponse.id,
                    name = getCompanyByTickerResponse.name,
                    tickers = getCompanyByTickerResponse.tickers,
                    sector = getCompanyByTickerResponse.sector)
        }

        return null
    }
}