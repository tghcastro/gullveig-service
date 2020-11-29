package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies

import de.undercouch.gradle.tasks.download.org.apache.http.client.methods.CloseableHttpResponse
import de.undercouch.gradle.tasks.download.org.apache.http.client.methods.HttpGet
import de.undercouch.gradle.tasks.download.org.apache.http.impl.client.HttpClientBuilder
import org.springframework.stereotype.Service
import java.net.URI

@Service
class CompaniesServiceHttpClient {

    //TODO: Refactor this
    fun getCompanyByTicker(ticker: String): CloseableHttpResponse {
        val url = URI("http://localhost:9002/api/v1/companies/$ticker")
        //val url = URI("http://localhost:8080/api/v1/companies/$ticker")

        val client = HttpClientBuilder.create().build()

        val getRequest = HttpGet(url)
        getRequest.addHeader("X-Mapping-Id", "MyValue")
        return client.execute(getRequest)
    }
}