package com.cr.o.cdc.lavayacoins.services

import com.cr.o.cdc.lavayacoins.db.Store
import com.cr.o.cdc.lavayacoins.inputs.StoreInput
import com.cr.o.cdc.lavayacoins.repos.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(storeRepository: StoreRepository) : BaseService<Store, String>(storeRepository) {


    fun save(storeInput: StoreInput): Store = save(
            Store(
                    storeInput.name,
                    storeInput.coordinatesInput.toCoordinates()
            )
    )!!

}