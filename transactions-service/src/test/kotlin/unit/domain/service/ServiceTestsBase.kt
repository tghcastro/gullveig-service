package unit.domain.service

import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsRepository
import com.tghcastro.gullveig.transactions.service.domain.services.TransactionsDomainService
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito

open class ServiceTestsBase {
    protected lateinit var transactionsService: TransactionsDomainService
    protected lateinit var transactionsRepository: TransactionsRepository

    @BeforeEach
    fun beforeEach() {
        transactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        transactionsService = TransactionsDomainService(transactionsRepository)

        Mockito.clearInvocations(transactionsRepository)
    }
}