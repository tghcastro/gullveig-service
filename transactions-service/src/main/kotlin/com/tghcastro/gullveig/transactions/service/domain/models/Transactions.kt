package com.tghcastro.gullveig.transactions.service.domain.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.tghcastro.gullveig.transactions.service.domain.results.DomainResult
import java.util.*
import javax.persistence.*
import javax.validation.constraints.DecimalMin
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

        @DecimalMin("0.1", message = "Transaction must have a price greater than 0.1")
        var price: Double,

        @ElementCollection
        var tags: List<String>,

        var type: DomainTransactionType,

        @DecimalMin("0.1", message = "Transaction must have units greater than 0.1")
        var units: Double
) {

    fun validate(): DomainResult<Transactions> {
        return DomainResult.success(this)
    }
}
