package com.cr.o.cdc.lavayacoins.repos

import com.cr.o.cdc.lavayacoins.db.AdminUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminUserRepository : JpaRepository<AdminUser, String>