package unit.domain.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import unit.domain.TestDomainHelper

class GetByIdTests : ServiceTestsBase() {
    @Test
    fun getById_ShouldReturnTransaction_WhenTransactionExists() {
        val transaction = TestDomainHelper.getValidExistentTransaction()
        Mockito.`when`(transactionsRepository.getById(ArgumentMatchers.any(Long::class.java))).thenReturn(transaction)
        val gottenTransaction = transactionsService.getById(1L)
        assertEquals(transaction, gottenTransaction)

        Mockito.verify(transactionsRepository, Mockito.times(1)).getById(1L)
    }

    @Test
    fun getById_ShouldReturnNull_WhenTransactionDoesNotExist() {
        Mockito.`when`(transactionsRepository.getById(ArgumentMatchers.any(Long::class.java))).thenReturn(null)
        val gottenTransaction = transactionsService.getById(1L)
        assertNull(gottenTransaction)
        Mockito.verify(transactionsRepository, Mockito.times(1)).getById(1L)
    }
}