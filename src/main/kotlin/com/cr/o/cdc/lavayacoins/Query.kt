package com.cr.o.cdc.lavayacoins

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.cr.o.cdc.lavayacoins.model.Store
import org.springframework.stereotype.Component

@Component
class Query : GraphQLQueryResolver {

    fun findAllStores(): List<Store> {
        return emptyList()
    }

}