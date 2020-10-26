package unit.domain.service

import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsRepository
import com.tghcastro.gullveig.transactions.service.domain.services.TransactionsDomainService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import unit.domain.TestDomainHelper

class GetByIdTests {
    lateinit var transactionsService: TransactionsDomainService
    lateinit var transactionsRepository: TransactionsRepository

    @BeforeEach
    fun beforeEach() {
        transactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        transactionsService = TransactionsDomainService(transactionsRepository)

        Mockito.clearInvocations(transactionsRepository)
    }

    @Test
    fun getById_ShouldReturnTransaction_WhenTransactionExists() {
        val transaction = TestDomainHelper.getValidTransaction()
        Mockito.`when`(transactionsRepository.getById(ArgumentMatchers.any(Long::class.java))).thenReturn(transaction)
        val gottenTransaction = transactionsService.getById(1L)
        assertEquals(transaction, gottenTransaction)
    }

    @Test
    fun getById_ShouldReturnNull_WhenTransactionDoesNotExist() {
        Mockito.`when`(transactionsRepository.getById(ArgumentMatchers.any(Long::class.java))).thenReturn(null)
        val gottenTransaction = transactionsService.getById(1L)
        assertNull(gottenTransaction)
    }
}