package com.cr.o.cdc.lavayacoins.db

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Store(
        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String,
        val name: String?,
        @Embedded val coordinates: Coordinates
)