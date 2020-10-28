package unit.domain.service

import com.tghcastro.gullveig.transactions.service.domain.interfaces.CompaniesServiceClient
import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsRepository
import com.tghcastro.gullveig.transactions.service.domain.services.TransactionsDomainService
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito

open class ServiceTestsBase {
    protected lateinit var transactionsService: TransactionsDomainService
    protected lateinit var transactionsRepository: TransactionsRepository
    protected lateinit var companiesServiceClient: CompaniesServiceClient

    @BeforeEach
    fun beforeEach() {
        transactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        companiesServiceClient = Mockito.mock(CompaniesServiceClient::class.java)
        transactionsService = TransactionsDomainService(transactionsRepository, companiesServiceClient)

        Mockito.clearInvocations(transactionsRepository)
        Mockito.clearInvocations(companiesServiceClient)
    }
}