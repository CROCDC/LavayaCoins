package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.fakes.MockFactory
import com.cr.o.cdc.lavayacoins.fakes.MockFactory.toStore
import com.cr.o.cdc.lavayacoins.services.AdminUserService
import com.cr.o.cdc.lavayacoins.services.CustomerUserService
import com.cr.o.cdc.lavayacoins.services.StoreService
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class MutationServiceIntegrationTest {


    @Test
    fun saveStore() {
        val store = MockFactory.getStoreInput().toStore()
        val storeService = Mockito.mock(StoreService::class.java).apply {
            Mockito.`when`(save(store)).thenReturn(store)
        }
        getMutation(storeService = storeService).saveStore(
                MockFactory.getSaveStoreInput()
        )
        verify(storeService, times(1)).save(store)
    }

    private fun getMutation(
            customerUserService: CustomerUserService? = null,
            adminUserService: AdminUserService? = null,
            storeService: StoreService? = null
    ): Mutation = Mutation(
            customerUserService ?: Mockito.mock(CustomerUserService::class.java),
            adminUserService ?: Mockito.mock(AdminUserService::class.java),
            storeService ?: Mockito.mock(StoreService::class.java)
    )

}