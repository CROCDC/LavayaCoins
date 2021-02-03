package com.cr.o.cdc.lavayacoins.services

import com.cr.o.cdc.lavayacoins.db.AdminUser
import com.cr.o.cdc.lavayacoins.repos.AdminUserRepository
import org.springframework.stereotype.Service

@Service
class AdminUserService(adminUserRepository: AdminUserRepository) :
        BaseService<AdminUser, String>(adminUserRepository)