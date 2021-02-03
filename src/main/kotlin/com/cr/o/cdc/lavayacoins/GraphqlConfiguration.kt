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
                    .add(LoginAdminSuccess::class)
                    .add(LoginAdminUserError::class)
                    .add(CreateAdminResult::class)
                    .add(CreateAdminSuccess::class)
                    .add(CreateAdminUserError::class)
}

