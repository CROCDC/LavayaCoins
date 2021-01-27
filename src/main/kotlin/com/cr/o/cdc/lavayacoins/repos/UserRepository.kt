package com.cr.o.cdc.lavayacoins.repos

import com.cr.o.cdc.lavayacoins.model.CustomerUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<CustomerUser, String> {


}