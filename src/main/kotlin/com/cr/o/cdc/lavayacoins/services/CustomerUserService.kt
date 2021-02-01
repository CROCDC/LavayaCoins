package com.cr.o.cdc.lavayacoins.services

import com.cr.o.cdc.lavayacoins.db.CustomerUser
import com.cr.o.cdc.lavayacoins.repos.UserRepository
import org.springframework.stereotype.Service

@Service
class CustomerUserService(userRepository: UserRepository) : BaseService<CustomerUser, String>(userRepository)