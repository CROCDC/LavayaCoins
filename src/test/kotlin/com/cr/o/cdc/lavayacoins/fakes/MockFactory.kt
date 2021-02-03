package com.cr.o.cdc.lavayacoins.fakes

import com.cr.o.cdc.lavayacoins.db.AdminUser
import com.cr.o.cdc.lavayacoins.db.CustomerUser
import com.cr.o.cdc.lavayacoins.utils.Authority

object MockFactory {

    fun getPasswordRomeroCamilo03() = "110599"

    fun getUsernameRomeroCamilo03() = "romerocamilo03"

    fun getUsernameDummy() = "Dummy"

    fun getCustomerUserRomeroCamilo03() = CustomerUser(
            getUsernameRomeroCamilo03(),
            getPasswordRomeroCamilo03()
    )

    fun getCustomerUserDummy() = CustomerUser(
            getUsernameDummy(),
            "1234"
    )

    fun getAdminUsername() = "admin"

    fun getAdminPassword() = "admin"

    fun getAdminUser() = AdminUser(
            getAdminUsername(),
            getAdminPassword(),
            listOf(Authority.CREATE_ADMINS)
    )
}