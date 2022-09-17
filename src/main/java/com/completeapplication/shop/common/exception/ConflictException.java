package com.completeapplication.shop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConflictException extends RuntimeException {
    private final Object msg;
}
