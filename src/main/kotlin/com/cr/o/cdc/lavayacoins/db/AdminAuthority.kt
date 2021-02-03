package com.cr.o.cdc.lavayacoins.db

import com.cr.o.cdc.lavayacoins.utils.Authority
import javax.persistence.Embeddable

@Embeddable
data class AdminAuthority(
        val authority: Authority,
        val creatorUsername: String,
        val reason: String?
)