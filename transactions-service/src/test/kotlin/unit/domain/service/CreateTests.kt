package unit.domain.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import unit.domain.TestDomainHelper

class CreateTests : ServiceTestsBase() {

    @Test
    fun create_ShouldReturnCreatedTransaction_WhenGivenTransactionIsValid() {
        val transaction = TestDomainHelper.getValidExistentTransaction()
        val expectedTransaction = TestDomainHelper.getValidExistentTransaction()
        Mockito.`when`(transactionsRepository.saveAndFlush(transaction)).thenReturn(expectedTransaction)
        val result = transactionsService.create(transaction)
        Assertions.assertEquals(expectedTransaction, result.value())

        Mockito.verify(transactionsRepository, times(1)).saveAndFlush(transaction)
    }
}