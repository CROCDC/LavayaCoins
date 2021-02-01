package com.cr.o.cdc.lavayacoins.responses

import com.cr.o.cdc.lavayacoins.db.CustomerUser

data class AdminUserCredentials(val user: CustomerUser, val credentials: Credentials)