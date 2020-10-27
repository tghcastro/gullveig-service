package com.tghcastro.gullveig.transactions.service.infrastructure.repositories.jpa

import com.tghcastro.gullveig.transactions.service.domain.interfaces.TransactionsRepository
import com.tghcastro.gullveig.transactions.service.domain.models.Transactions
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionsJpaRepository : JpaRepository<Transactions, Long>, TransactionsRepository