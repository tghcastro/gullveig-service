package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies

import com.tghcastro.gullveig.transactions.service.domain.models.Companies
import com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies.contracts.GetCompanyByTicker
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.host
import io.ktor.client.request.port
import io.ktor.http.HttpMethod
import org.springframework.stereotype.Service

@Service
class CompaniesServiceHttpClient {
    suspend fun getCompanyByTicker(ticker: String): GetCompanyByTicker {
        val client = HttpClient(Apache) {
            this.engine {
                this.connectTimeout = 1000
                this.followRedirects = true
            }
            defaultRequest { // this: HttpRequestBuilder ->
                method = HttpMethod.Get
                host = "127.0.0.1"
                port = 9002
                header("X-Mapping-Id", "MyValue")
            }
        }

        return client.get<GetCompanyByTicker> {
            url {
                fragment = "/api/v1/companies/$ticker"
            }
        }
    }
}