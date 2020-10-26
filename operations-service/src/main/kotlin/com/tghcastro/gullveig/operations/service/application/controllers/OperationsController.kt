package com.tghcastro.gullveig.operations.service.application.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OperationsController {

    @GetMapping("/test")
    fun test(@RequestParam(value = "name", defaultValue = "World") name: String): String {
        return "test $name"
    }
}