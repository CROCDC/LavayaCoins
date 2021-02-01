package com.cr.o.cdc.lavayacoins.services

import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getCustomerUserDummy
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getCustomerUserRomeroCamilo03
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getUsernameDummy
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.getUsernameRomeroCamilo03
import com.cr.o.cdc.lavayacoins.testutils.ServiceTest
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerUserServiceTest : ServiceTest<CustomerUserService>() {

    @Test
    fun findByIdWhenNotExist() {
        assertEquals(
                null,
                repository.findById("")
        )
    }

    @Test
    fun findByIdWhenExist() {
        assertEquals(
                getCustomerUserRomeroCamilo03(),
                repository.findById(getUsernameRomeroCamilo03())
        )
    }

    @Test
    fun saveWhenExist() {
        assertEquals(
                null,
                repository.save(getCustomerUserRomeroCamilo03(), getUsernameRomeroCamilo03())
        )
    }

    @Test
    fun saveWhenNotExist() {
        assertEquals(
                getCustomerUserDummy(),
                repository.save(getCustomerUserDummy(), getUsernameDummy())
        )
    }
}