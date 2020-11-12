package com.tghcastro.gullveig.transactions.service.application.controllers.filters

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(1)
class TransactionFilter : Filter {
    companion object {
        const val CORRELATION_ID_HEADER = "X-CorrelationId"
    }


    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain?) {

        val httpServletRequest = servletRequest as HttpServletRequest
        val headersLog = generateRequestHeadersLog(httpServletRequest)
        println("Received request [${httpServletRequest.requestURI}] $headersLog")

        chain?.doFilter(servletRequest, servletResponse)

        val httpServletResponse = servletResponse as HttpServletResponse

        httpServletResponse.addHeader(CORRELATION_ID_HEADER, resolveCorrelationId(httpServletRequest))

        val responseHeadersLog = generateResponseHeadersLog(httpServletResponse)
        println("Response [${httpServletRequest.requestURI}][${httpServletResponse.status}]$responseHeadersLog")

    }

    private fun resolveCorrelationId(httpServletRequest: HttpServletRequest): String {
        if (httpServletRequest.getHeader(CORRELATION_ID_HEADER) != null) {
            return httpServletRequest.getHeader(CORRELATION_ID_HEADER)
        }
        return UUID.randomUUID().toString()
    }

    private fun generateRequestHeadersLog(httpServletRequest: HttpServletRequest): String {
        var log = ""
        for (headerName in httpServletRequest.headerNames) {
            val headerValue = httpServletRequest.getHeader(headerName)
            log += "[$headerName:$headerValue]"
        }
        return log
    }

    private fun generateResponseHeadersLog(httpServletResponse: HttpServletResponse): String {
        var log = ""
        for (headerName in httpServletResponse.headerNames) {
            val headerValue = httpServletResponse.getHeader(headerName)
            log += "[$headerName:$headerValue]"
        }
        return log
    }
}