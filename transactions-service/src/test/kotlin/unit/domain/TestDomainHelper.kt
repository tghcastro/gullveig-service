package unit.domain

import com.tghcastro.gullveig.transactions.service.domain.models.DomainTransactionType
import com.tghcastro.gullveig.transactions.service.domain.models.Transactions
import java.time.Instant
import java.util.*

class TestDomainHelper {
    companion object {
        fun getValidExistentTransaction(): Transactions {
            return Transactions(
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
        }

        fun getValidInexistentTransaction(): Transactions {
            val transaction = getValidExistentTransaction()
            transaction.id = null
            return transaction
        }
    }
}