package com.cr.o.cdc.lavayacoins.responses

import com.cr.o.cdc.lavayacoins.db.AdminUser

data class LoginAdminSuccess(val user: AdminUser, val credentials: Credentials) : LoginAdminResult