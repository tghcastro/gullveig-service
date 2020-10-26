package unit.domain

import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsRepository
import com.tghcastro.gullveig.transactions.service.domain.models.DomainTransactionType
import com.tghcastro.gullveig.transactions.service.domain.models.Transactions
import com.tghcastro.gullveig.transactions.service.domain.services.TransactionsDomainService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import java.time.Instant
import java.util.*

class TransactionsServiceTests {
    lateinit var transactionsService: TransactionsDomainService
    lateinit var transactionsRepository: TransactionsRepository

    @BeforeEach
    fun beforeEach() {
        transactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        transactionsService = TransactionsDomainService(transactionsRepository)
    }


    @Test
    fun getById_ShouldReturnTransaction_WhenTransactionExists() {

        val transaction = Transactions(
                id = 1L,
                ticker = "KO",
                units = 10.0,
                type = DomainTransactionType.SELL,
                tags = listOf("SURE PASSSIVE INCOME"),
                price = 50.0,
                currencyCode = "USD",
                costs = 0.51,
                date = Date.from(Instant.now())
        )

        Mockito.`when`(transactionsRepository.getById(ArgumentMatchers.any(Long::class.java))).thenReturn(transaction)

        val gottenTransaction = transactionsService.getById(1L)

        assertEquals(transaction, gottenTransaction)

    }
}