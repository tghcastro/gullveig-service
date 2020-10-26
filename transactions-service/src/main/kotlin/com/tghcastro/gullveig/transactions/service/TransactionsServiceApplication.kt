package com.tghcastro.gullveig.transactions.service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TransactionsServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(TransactionsServiceApplication::class.java, *args)
        }
    }

}