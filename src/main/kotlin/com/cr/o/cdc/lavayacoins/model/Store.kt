package com.cr.o.cdc.lavayacoins.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.GeneratedValue
import javax.persistence.Id

data class Store(
        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String,
        val name: String?,
        val coordinates: Coordinates
)