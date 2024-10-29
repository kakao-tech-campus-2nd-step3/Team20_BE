package com.gamsa.common.utils;

import jakarta.servlet.http.HttpServletRequest;

public class ExtractUserIdFromJwt {

    public static Long extract(HttpServletRequest request) {
        return (Long) request.getAttribute("userId");
    }

}
