package com.cr.o.cdc.lavayacoins.repos

import com.cr.o.cdc.lavayacoins.db.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, String>