package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.db.Store
import com.cr.o.cdc.lavayacoins.repos.StoreRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class Query(val storeRepository: StoreRepository) : GraphQLQueryResolver {

    fun findAllStores(): List<Store> = storeRepository.findAll()

}