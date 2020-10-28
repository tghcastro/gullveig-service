package com.tghcastro.gullveig.transactions.service.tests.unit.domain.services

import com.tghcastro.gullveig.transactions.service.tests.unit.domain.TestDomainHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.times

class CreateTests : ServiceTestsBase() {

    @Test
    fun create_ShouldReturnCreatedTransaction_WhenGivenTransactionIsValid() {
        val transaction = TestDomainHelper.getValidExistentTransaction()
        val expectedTransaction = TestDomainHelper.getValidExistentTransaction()
        val company = TestDomainHelper.getExistentCompany()

        Mockito.`when`(transactionsRepository.saveAndFlush(transaction)).thenReturn(expectedTransaction)
        Mockito.`when`(companiesServiceClient.getCompanyByTicker(transaction.ticker)).thenReturn(company)

        val result = transactionsService.create(transaction)

        Assertions.assertTrue(result.succeeded())
        Assertions.assertEquals(expectedTransaction, result.value())
        Mockito.verify(transactionsRepository, times(1)).saveAndFlush(transaction)
    }

    @Test
    fun create_ShouldReturnFailure_WhenGivenTransactionIsValid_ButStockDoesntExist() {
        val transaction = TestDomainHelper.getValidExistentTransaction()
        transaction.ticker = "NOT"

        Mockito.`when`(companiesServiceClient.getCompanyByTicker(transaction.ticker)).thenReturn(null)

        val result = transactionsService.create(transaction)
        Assertions.assertTrue(result.failed())
        Assertions.assertTrue(result.error()!!.contains("Ticker does not exist"))

        Mockito.verify(transactionsRepository, never()).saveAndFlush(transaction)
        Mockito.verify(companiesServiceClient, times(1)).getCompanyByTicker(transaction.ticker)
    }
}