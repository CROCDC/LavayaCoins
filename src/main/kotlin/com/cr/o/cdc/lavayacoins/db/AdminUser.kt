package com.cr.o.cdc.lavayacoins.db

import com.cr.o.cdc.lavayacoins.utils.Authority
import javax.persistence.*

@Entity
data class AdminUser(
        @Id
        val username: String,
        val password: String,
        @ElementCollection
        @CollectionTable(
                name = "AdminAuthority",
                joinColumns = [JoinColumn(name = "username")]
        )
        @Column(name = "adminAuthority")
        val adminAuthorities: List<AdminAuthority>
)