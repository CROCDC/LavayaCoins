package com.cr.o.cdc.lavayacoins.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import java.util.*


object JWTToken {

    fun validateJWTToken(token: String): String? {
        return JWT.require(HMAC512(SECRET))
                .build()
                .verify(token)
                .subject
    }

    fun getJWTToken(username: String): String {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(Date(System.currentTimeMillis() + 864_000_000))
                .sign(HMAC512(SECRET))

    }

    private const val SECRET = "SecretKeyToGenJWTs"
}