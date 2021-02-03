package com.cr.o.cdc.lavayacoins.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*


object JWTToken {

    fun validateJWTToken(token: String, withAuthorities: List<Authority>): String? =
            if (getAuthorities(token) == withAuthorities) {
                getJWT(token)?.subject
            } else {
                null
            }


    private fun getJWT(token: String): DecodedJWT? = try {
        JWT.require(HMAC512(SECRET))
                .build()
                .verify(token)
    } catch (e: Exception) {
        null
    }


    fun getAuthorities(token: String): List<Authority> = getJWT(token)?.claims?.get(AUTHORITIES_KEY)
            ?.asArray(Authority::class.java)?.toList() ?: emptyList()

    fun getJWTToken(username: String, authorities: List<Authority>, expireTime: Int? = null): String = JWT.create()
            .withSubject(username)
            .withArrayClaim(AUTHORITIES_KEY, authorities.map {
                it.name
            }.toTypedArray())
            .withExpiresAt(Date(System.currentTimeMillis() + (expireTime ?: DEFAULT_EXPIRE_TIME)))
            .sign(HMAC512(SECRET))


    private const val SECRET = "SecretKeyToGenJWTs"
    private const val AUTHORITIES_KEY = "authorities"
    private const val DEFAULT_EXPIRE_TIME = 864000000
}

enum class Authority {
    ADMIN_STORES,
    SEND_TIPS,
    CREATE_ADMINS
}