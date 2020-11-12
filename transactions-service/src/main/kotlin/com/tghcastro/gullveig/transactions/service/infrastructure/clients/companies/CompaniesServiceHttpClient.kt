package com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies

import com.fasterxml.jackson.databind.ObjectMapper
import com.tghcastro.gullveig.transactions.service.infrastructure.clients.companies.contracts.GetCompanyByTicker
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.stereotype.Service
import java.net.URI

@Service
class CompaniesServiceHttpClient {

    //TODO: Refactor this
    fun getCompanyByTicker(ticker: String): GetCompanyByTicker? {
        val url = URI("http://localhost:9002/api/v1/companies/$ticker")
        //val url = URI("http://localhost:8080/api/v1/companies/$ticker")

        val client = HttpClientBuilder.create().build()

        val getRequest = HttpGet(url)
        getRequest.addHeader("X-Mapping-Id", "MyValue")
        val response = client.execute(getRequest)

        val mapper = ObjectMapper()

        return mapper.readValue(response.entity.content, GetCompanyByTicker::class.java)
    }
}