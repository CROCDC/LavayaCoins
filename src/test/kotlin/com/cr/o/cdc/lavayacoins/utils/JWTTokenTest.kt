package com.cr.o.cdc.lavayacoins.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

class JWTTokenTest {


    @Test
    fun validateJWTTokenWithAuthorities_ADMIN_STORES_assert_has_authority_ADMIN_STORES() {
        val authorities = listOf(Authority.ADMIN_STORES)
        val accessToken = JWTToken.getJWTToken("username", authorities)

        assertEquals(
                "username",
                JWTToken.validateJWTToken(accessToken, authorities)
        )
    }

    @Test
    fun validateJWTTokenWithAuthorities_SEND_TIPS_assert_has_authority_ADMIN_STORES() {
        val accessToken = JWTToken.getJWTToken("username", listOf(Authority.SEND_TIPS))

        assertEquals(
                null,
                JWTToken.validateJWTToken(accessToken, listOf(Authority.ADMIN_STORES))
        )
    }

    @Test
    fun validateJWTokenWhenExpires() {
        val accessToken = JWTToken.getJWTToken("username", listOf(Authority.SEND_TIPS), -10)

        assertEquals(
                null,
                JWTToken.validateJWTToken(accessToken, listOf(Authority.ADMIN_STORES))
        )
    }
}
