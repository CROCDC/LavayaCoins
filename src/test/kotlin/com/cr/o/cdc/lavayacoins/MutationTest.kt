package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getAdminPassword
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getAdminUser
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getAdminUsername
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getCustomerUserRomeroCamilo03
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getPasswordRomeroCamilo03
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getUsernameRomeroCamilo03
import com.cr.o.cdc.lavayacoins.inputs.LoginAdminUserInput
import com.cr.o.cdc.lavayacoins.inputs.LoginCustomerUserInput
import com.cr.o.cdc.lavayacoins.repos.StoreRepository
import com.cr.o.cdc.lavayacoins.responses.*
import com.cr.o.cdc.lavayacoins.services.AdminUserService
import com.cr.o.cdc.lavayacoins.services.CustomerUserService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.Mockito

class MutationTest {


    @Test
    fun loginCustomerUserWithDifferentPassword() {
        val mutation = getMutation(
                Mockito.mock(CustomerUserService::class.java).apply {
                    Mockito.`when`(findById(getUsernameRomeroCamilo03())).thenReturn(getCustomerUserRomeroCamilo03())
                }
        )

        assertEquals(
                LoginCustomerError(LoginCustomerErrorCause.PASSWORD_MISMATCH),
                mutation.loginCustomerUser(LoginCustomerUserInput(getUsernameRomeroCamilo03(), "1234"))
        )
    }

    @Test
    fun loginAdminUserWithDifferentPassword() {
        val mutation = getMutation(
                adminUserService = Mockito.mock(AdminUserService::class.java).apply {
                    Mockito.`when`(findById(getAdminUsername())).thenReturn(getAdminUser())
                }
        )

        assertEquals(
                LoginAdminError(LoginAdminErrorCause.PASSWORD_MISMATCH),
                mutation.loginAdminUser(LoginAdminUserInput(getAdminUsername(), "1234"))
        )
    }

    @Test
    fun loginAdminWhenNotExist() {
        val mutation = getMutation(
                adminUserService = Mockito.mock(AdminUserService::class.java).apply {
                    Mockito.`when`(findById(getAdminUsername())).thenReturn(getAdminUser())
                }
        )

        assertEquals(
                LoginAdminError(LoginAdminErrorCause.USER_NOT_EXIST),
                mutation.loginAdminUser(LoginAdminUserInput("not exist", "1234"))
        )
    }

    @Test
    fun loginCustomerWhenNotExist(){
        val mutation = getMutation(
                Mockito.mock(CustomerUserService::class.java).apply {
                    Mockito.`when`(findById(getUsernameRomeroCamilo03())).thenReturn(getCustomerUserRomeroCamilo03())
                }
        )

        assertEquals(
                LoginCustomerError(LoginCustomerErrorCause.USER_NOT_EXIST),
                mutation.loginCustomerUser(LoginCustomerUserInput("not exist", "1234"))
        )
    }

    @Test
    fun loginCustomerUserWithSamePassword() {
        val mutation = getMutation(
                Mockito.mock(CustomerUserService::class.java).apply {
                    Mockito.`when`(findById(getUsernameRomeroCamilo03())).thenReturn(getCustomerUserRomeroCamilo03())
                }
        )

        assertTrue(
                mutation.loginCustomerUser(LoginCustomerUserInput(
                        getUsernameRomeroCamilo03(),
                        getPasswordRomeroCamilo03())
                ) is LoginCustomerSuccess
        )
    }

    @Test
    fun loginAdminUserWithSamePassword() {
        val mutation = getMutation(
                adminUserService = Mockito.mock(AdminUserService::class.java).apply {
                    Mockito.`when`(findById(getAdminUsername())).thenReturn(getAdminUser())
                }
        )

        assertTrue(
                mutation.loginAdminUser(LoginAdminUserInput(getAdminUsername(), getAdminPassword()))
                        is LoginAdminSuccess
        )
    }

    private fun getMutation(
            customerUserService: CustomerUserService? = null,
            adminUserService: AdminUserService? = null
    ): Mutation = Mutation(
            customerUserService ?: Mockito.mock(CustomerUserService::class.java),
            adminUserService ?: Mockito.mock(AdminUserService::class.java),
            Mockito.mock(StoreRepository::class.java)
    )


}