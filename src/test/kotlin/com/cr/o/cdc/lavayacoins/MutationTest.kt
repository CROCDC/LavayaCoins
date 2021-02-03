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
import com.cr.o.cdc.lavayacoins.responses.CustomerUserCredentials
import com.cr.o.cdc.lavayacoins.responses.LoginAdminErrorCause
import com.cr.o.cdc.lavayacoins.responses.LoginAdminSuccess
import com.cr.o.cdc.lavayacoins.responses.LoginAdminUserError
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
                null,
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
                LoginAdminUserError(LoginAdminErrorCause.PASSWORD_MISMATCH),
                mutation.loginAdminUser(LoginAdminUserInput(getAdminUsername(), "1234"))
        )
    }

    @Test
    fun loginAdminWhenNotExist(){
        val mutation = getMutation(
                adminUserService = Mockito.mock(AdminUserService::class.java).apply {
                    Mockito.`when`(findById(getAdminUsername())).thenReturn(getAdminUser())
                }
        )

        assertEquals(
                LoginAdminUserError(LoginAdminErrorCause.USER_NOT_EXIST),
                mutation.loginAdminUser(LoginAdminUserInput("not exist", "1234"))
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
                ) is CustomerUserCredentials
        )
    }

    @Test
    fun loginAdminUserWithSamePassword() {
        val mutation = getMutation(
               adminUserService =  Mockito.mock(AdminUserService::class.java).apply {
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