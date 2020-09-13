package com.tghcastro.gullveig.stocks.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Long id) {
        super(MessageFormat.format("Stock not found [id:{0}]", id));
    }
}
