package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies

import com.tghcastro.gullveig.transactions.service.domain.interfaces.CompaniesServiceClient
import com.tghcastro.gullveig.transactions.service.domain.models.Companies
import com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies.contracts.GetCompanyByTicker
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.host
import io.ktor.client.request.port
import io.ktor.http.HttpMethod
import org.springframework.stereotype.Service
import kotlinx.coroutines.*

@Service
class CompaniesServiceClientImpl(val httpClient: CompaniesServiceHttpClient) : CompaniesServiceClient {
    override fun getCompanyByTicker(ticker: String): Companies? {

        try {
            val getCompanyByTicker = runBlocking { httpClient.getCompanyByTicker(ticker) }
        } catch (e: Exception) {
            val a = e;
        }


        return null
    }
}