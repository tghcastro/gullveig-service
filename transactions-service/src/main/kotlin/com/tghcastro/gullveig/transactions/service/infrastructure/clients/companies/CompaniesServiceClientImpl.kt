package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.tghcastro.gullveig.transactions.service.domain.interfaces.CompaniesServiceClient
import com.tghcastro.gullveig.transactions.service.domain.models.Companies
import com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies.contracts.GetCompanyByTicker
import org.springframework.stereotype.Service

@Service
class CompaniesServiceClientImpl(val httpClient: CompaniesServiceHttpClient) : CompaniesServiceClient {
    override fun getCompanyByTicker(ticker: String): HttpClientResult<Companies> {
        val response = httpClient.getCompanyByTicker(ticker)

        if (response.statusLine.statusCode >= 400 && response.statusLine.statusCode < 500) {
            return HttpClientResult.failure(response, "Company not found by ticker [$ticker]", HttpClientResult.ClientErrorType.CLIENT)
        }

        if (response.statusLine.statusCode >= 500) {
            return HttpClientResult.failure(response, "Downstream error", HttpClientResult.ClientErrorType.SERVER)
        }

        val mapper = ObjectMapper().registerModule(KotlinModule())
        val getCompanyByTickerResponse = mapper.readValue(response.entity.content, GetCompanyByTicker::class.java)

        val company = Companies(
                id = getCompanyByTickerResponse.id,
                name = getCompanyByTickerResponse.name,
                tickers = getCompanyByTickerResponse.tickers,
                sector = getCompanyByTickerResponse.sector)

        return HttpClientResult.success(company)
    }
}