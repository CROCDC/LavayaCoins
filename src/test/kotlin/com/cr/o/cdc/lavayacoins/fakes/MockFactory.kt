package com.cr.o.cdc.lavayacoins.fakes

import com.cr.o.cdc.lavayacoins.db.AdminUser
import com.cr.o.cdc.lavayacoins.db.CustomerUser
import com.cr.o.cdc.lavayacoins.db.Store
import com.cr.o.cdc.lavayacoins.inputs.CoordinatesInput
import com.cr.o.cdc.lavayacoins.inputs.SaveStoreInput
import com.cr.o.cdc.lavayacoins.inputs.StoreInput
import com.cr.o.cdc.lavayacoins.utils.Authority
import com.cr.o.cdc.lavayacoins.utils.JWTToken

object MockFactory {

    private fun getAccessTokenWith_ADMIN_STORES_Authoriy(): String = JWTToken.getJWTToken(
            "camilo",
            listOf(Authority.ADMIN_STORES)
    )

    fun getPasswordRomeroCamilo03(): String = "110599"

    fun getUsernameRomeroCamilo03(): String = "romerocamilo03"

    fun getUsernameDummy(): String = "Dummy"

    fun getCustomerUserRomeroCamilo03(): CustomerUser = CustomerUser(
            getUsernameRomeroCamilo03(),
            getPasswordRomeroCamilo03(),
            getAuthoritiesOfCustomerUser()
    )

    fun getUsernameDelete(): String = "delete"

    fun getPasswordDelete(): String = "delete"

    fun getAuthoritiesOfCustomerUser(): List<Authority> = listOf(Authority.SEND_TIPS)

    fun getCustomerUserDelete():CustomerUser = CustomerUser(
            getUsernameDelete(),
            getPasswordDelete(),
            getAuthoritiesOfCustomerUser()
    )

    fun getCustomerUserDummy(): CustomerUser = CustomerUser(
            getUsernameDummy(),
            "1234",
            getAuthoritiesOfCustomerUser()
    )

    fun getAdminUsername(): String = "admin"

    fun getAdminPassword(): String = "admin"

    fun getAdminUser(): AdminUser = AdminUser(
            getAdminUsername(),
            getAdminPassword(),
            listOf(Authority.CREATE_ADMINS)
    )

    fun getStoreId(): String = "storeId"

    fun getStoreCoordinates(): CoordinatesInput = CoordinatesInput(
            20f,
            20f
    )

    fun getStoreInput(): StoreInput = StoreInput(
            "name",
            getStoreCoordinates()
    )

    fun getSaveStoreInput(): SaveStoreInput = SaveStoreInput(
            getStoreInput(),
            getAccessTokenWith_ADMIN_STORES_Authoriy()
    )

    fun StoreInput.toStore(): Store = Store(
            name,
            coordinatesInput.toCoordinates()
    )
}