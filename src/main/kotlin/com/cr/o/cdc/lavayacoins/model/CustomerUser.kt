package com.cr.o.cdc.lavayacoins.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class CustomerUser(
        @Id
        val username: String,
        val password: String
)