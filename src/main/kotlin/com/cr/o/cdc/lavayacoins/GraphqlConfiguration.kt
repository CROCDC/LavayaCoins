package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.responses.*
import graphql.kickstart.tools.SchemaParserDictionary
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphqlConfiguration {

    @Bean
    fun schemaParserDictionary(): SchemaParserDictionary =
            SchemaParserDictionary()
                    .add(SaveStoreSuccess::class)
                    .add(SaveStoreResult::class)
                    .add(SaveStoreErrorInvalidAuthorities::class)
                    .add(CreateAdminResult::class)
                    .add(CreateAdminSuccess::class)
                    .add(CreateAdminError::class)
                    .add(CreateCustomerResult::class)
                    .add(CreateCustomerError::class)
                    .add(CreateCustomerSuccess::class)
                    .add(LoginAdminResult::class)
                    .add(LoginAdminError::class)
                    .add(LoginAdminSuccess::class)
                    .add(LoginCustomerSuccess::class)
                    .add(LoginCustomerError::class)
                    .add(LoginCustomerSuccess::class)
                    .add(SaveStoresResult::class)
                    .add(SaveStoresSuccess::class)
                    .add(DeleteStoreResult::class)
                    .add(DeleteStoreSuccess::class)
                    .add(DeleteStoreNotExist::class)
                    .add(DeleteStoreInvalidAuthorities::class)
}

