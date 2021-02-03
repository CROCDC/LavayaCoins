package com.cr.o.cdc.lavayacoins.inputs

import com.cr.o.cdc.lavayacoins.utils.Authority

data class CreateAdminUserInput(
        val username: String,
        val password: String,
        val accessToken: String,
        val authorities: List<Authority>
)