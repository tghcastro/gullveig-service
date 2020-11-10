package com.tghcastro.gullveig.transactions.service.infrastructure.clients

import com.tghcastro.gullveig.transactions.service.domain.interfaces.CompaniesServiceClient
import com.tghcastro.gullveig.transactions.service.domain.models.Companies

class CompaniesServiceClientImpl : CompaniesServiceClient {
    override fun getCompanyByTicker(ticker: String): Companies? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}