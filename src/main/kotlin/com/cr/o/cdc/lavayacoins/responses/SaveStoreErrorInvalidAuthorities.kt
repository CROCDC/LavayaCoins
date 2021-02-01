package com.cr.o.cdc.lavayacoins.responses

import com.cr.o.cdc.lavayacoins.utils.Authority

data class SaveStoreErrorInvalidAuthorities(
        val neededAuthorities: List<Authority>,
        val sendAuthorities: List<Authority>
) : SaveStoreResult