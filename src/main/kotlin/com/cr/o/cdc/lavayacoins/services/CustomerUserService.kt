package com.cr.o.cdc.lavayacoins.services

import com.cr.o.cdc.lavayacoins.db.CustomerUser
import com.cr.o.cdc.lavayacoins.repos.CustomerUserRepository
import org.springframework.stereotype.Service

@Service
class CustomerUserService(customerUserRepository: CustomerUserRepository) :
        BaseService<CustomerUser, String>(customerUserRepository)