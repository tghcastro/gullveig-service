package com.tghcastro.gullveig.operations.service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OperationsServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(OperationsServiceApplication::class.java, *args)
        }
    }

}