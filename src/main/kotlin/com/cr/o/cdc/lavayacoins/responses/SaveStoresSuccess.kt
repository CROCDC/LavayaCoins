package com.cr.o.cdc.lavayacoins.responses

import com.cr.o.cdc.lavayacoins.db.Store
import com.cr.o.cdc.lavayacoins.responses.SaveStoresResult

data class SaveStoresSuccess(val stores: List<Store>) : SaveStoresResult
