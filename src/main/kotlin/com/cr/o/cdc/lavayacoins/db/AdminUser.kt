package com.cr.o.cdc.lavayacoins.db

import com.cr.o.cdc.lavayacoins.converters.AuthoritiesConverter
import com.cr.o.cdc.lavayacoins.utils.Authority
import javax.persistence.*

@Entity
data class AdminUser(
        @Id
        val username: String,
        val password: String,
        @Convert(converter = AuthoritiesConverter::class)
        val  authorities: List<Authority>
)