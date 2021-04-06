package com.cr.o.cdc.lavayacoins.responses

data class SaveStoreErrorInvalidAuthorities(
        val invalidAccessToken: String
) : SaveStoreResult, SaveStoresResult