package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getCustomerUserRomeroCamilo03
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getPasswordRomeroCamilo03
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getUsernameRomeroCamilo03
import com.cr.o.cdc.lavayacoins.inputs.LoginAdminUserInput
import com.cr.o.cdc.lavayacoins.inputs.LoginCustomerUserInput
import com.cr.o.cdc.lavayacoins.repos.StoreRepository
import com.cr.o.cdc.lavayacoins.services.CustomerUserService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.Mockito

class MutationTest {


    @Test
    fun loginCustomerUserWithDifferentPassword() {
        val mutation = Mutation(
                Mockito.mock(CustomerUserService::class.java).apply {
                    Mockito.`when`(findById(getUsernameRomeroCamilo03())).thenReturn(getCustomerUserRomeroCamilo03())
                },
                Mockito.mock(StoreRepository::class.java)
        )

        assertEquals(
                null,
                mutation.loginCustomerUser(LoginCustomerUserInput(getUsernameRomeroCamilo03(), "1234"))
        )
    }

    @Test
    fun loginAdminUserWithDifferentPassword() {
        val mutation = Mutation(
                Mockito.mock(CustomerUserService::class.java).apply {
                    Mockito.`when`(findById(getUsernameRomeroCamilo03())).thenReturn(getCustomerUserRomeroCamilo03())
                },
                Mockito.mock(StoreRepository::class.java)
        )

        assertEquals(
                null,
                mutation.loginAdminUser(LoginAdminUserInput(getUsernameRomeroCamilo03(), "1234"))
        )
    }

    @Test
    fun loginCustomerUserWithSamePassword() {
        val mutation = Mutation(
                Mockito.mock(CustomerUserService::class.java).apply {
                    Mockito.`when`(findById(getUsernameRomeroCamilo03())).thenReturn(getCustomerUserRomeroCamilo03())
                },
                Mockito.mock(StoreRepository::class.java)
        )

        assertTrue(
                mutation.loginCustomerUser(LoginCustomerUserInput(getUsernameRomeroCamilo03(), getPasswordRomeroCamilo03()))
                        != null
        )
    }

    @Test
    fun loginAdminUserWithSamePassword() {
        val mutation = Mutation(
                Mockito.mock(CustomerUserService::class.java).apply {
                    Mockito.`when`(findById(getUsernameRomeroCamilo03())).thenReturn(getCustomerUserRomeroCamilo03())
                },
                Mockito.mock(StoreRepository::class.java)
        )

        assertTrue(
                mutation.loginAdminUser(LoginAdminUserInput(getUsernameRomeroCamilo03(), getPasswordRomeroCamilo03()))
                        != null
        )
    }


}