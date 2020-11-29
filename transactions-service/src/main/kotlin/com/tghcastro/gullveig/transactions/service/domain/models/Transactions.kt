package com.tghcastro.gullveig.transactions.service.domain.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.tghcastro.gullveig.transactions.service.domain.results.DomainResult
import com.tghcastro.gullveig.transactions.service.domain.results.FailureType
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name = "transactions")
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
class Transactions(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Size(min = 1, max = 5, message = "Ticker should have between 1 and 5 characters")
        @NotEmpty(message = "Ticker is mandatory")
        var ticker: String,
        @NotNull(message = "Transaction must have a date")
        var date: Date,
        var costs: Double = 0.0,
        var currencyCode: String = "USD",
        var price: Double,
        @ElementCollection
        var tags: List<String>,
        var type: DomainTransactionType,
        var units: Double
) {

    fun validate(): DomainResult<Transactions> {
        if (this.price <= 0) {
            return DomainResult.failure(this, "Transaction must have a price greater than 0.0 [Price: $price]", FailureType.CLIENT)
        }
        if (this.units <= 0) {
            return DomainResult.failure(this, "Transaction must have units greater than 0.0 [Units: $units]", FailureType.CLIENT)
        }
        if (this.costs < 0) {
            return DomainResult.failure(this, "Transaction must have costs of at least 0 [Costs: $costs]", FailureType.CLIENT)
        }
        return DomainResult.success(this)
    }
}
