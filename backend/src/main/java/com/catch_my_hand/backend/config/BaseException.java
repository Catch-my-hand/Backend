package com.catch_my_hand.backend.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Throwable {
    private BaseResponseStatus status; // BaseResponseStatus 객체 매핑
}
