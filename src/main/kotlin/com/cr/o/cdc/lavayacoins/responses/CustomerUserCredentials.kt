package com.cr.o.cdc.lavayacoins.responses

import com.cr.o.cdc.lavayacoins.db.CustomerUser

data class CustomerUserCredentials(val user: CustomerUser, val credentials: Credentials)