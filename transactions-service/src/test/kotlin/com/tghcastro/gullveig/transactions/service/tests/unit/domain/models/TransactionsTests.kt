package com.tghcastro.gullveig.transactions.service.tests.unit.domain.models

import com.tghcastro.gullveig.transactions.service.tests.unit.domain.TestDomainHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TransactionsTests {

    @Test
    fun validate_ShouldReturnSuccess_WhenGivenTransactionIsValid() {
        val transaction = TestDomainHelper.getValidExistentTransaction()

        val result = transaction.validate()

        Assertions.assertTrue(result.succeeded())
    }

    @ParameterizedTest()
    @ValueSource(doubles = [-1.0, -0.001, 0.0])
    fun validate_ShouldReturnFailure_WhenGivenTransactionHasAnInvalidPrice(invalidValues: Double) {
        val transaction = TestDomainHelper.getValidExistentTransaction()
        transaction.price = invalidValues

        val result = transaction.validate()

        Assertions.assertTrue(result.failed())
        Assertions.assertNotNull(result.error())
    }

    @ParameterizedTest()
    @ValueSource(doubles = [-1.0, -0.001, 0.0])
    fun validate_ShouldReturnFailure_WhenGivenTransactionHasAnInvalidUnits(invalidValues: Double) {
        val transaction = TestDomainHelper.getValidExistentTransaction()
        transaction.units = invalidValues

        val result = transaction.validate()

        Assertions.assertTrue(result.failed())
        Assertions.assertNotNull(result.error())
    }

    @ParameterizedTest()
    @ValueSource(doubles = [-1.0, -0.001])
    fun validate_ShouldReturnFailure_WhenGivenTransactionHasAnInvalidCosts(invalidValues: Double) {
        val transaction = TestDomainHelper.getValidExistentTransaction()
        transaction.costs = invalidValues

        val result = transaction.validate()

        Assertions.assertTrue(result.failed())
        Assertions.assertNotNull(result.error())
    }

    @ParameterizedTest()
    @ValueSource(doubles = [0.0, 0.001])
    fun validate_ShouldReturnSuccess_WhenGivenTransactionHasZeroCosts(validValues: Double) {
        val transaction = TestDomainHelper.getValidExistentTransaction()
        transaction.costs = validValues

        val result = transaction.validate()

        Assertions.assertTrue(result.succeeded())
        Assertions.assertNull(result.error())
    }
}