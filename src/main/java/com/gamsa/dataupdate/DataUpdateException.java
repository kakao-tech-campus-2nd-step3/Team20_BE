package com.gamsa.dataupdate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DataUpdateException extends RuntimeException {
    private final DataUpdateErrorCode errorCode;
}
