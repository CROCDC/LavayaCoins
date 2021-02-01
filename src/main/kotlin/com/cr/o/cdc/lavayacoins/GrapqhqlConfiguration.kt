package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.responses.SaveStoreErrorInvalidAuthorities
import com.cr.o.cdc.lavayacoins.responses.SaveStoreResult
import com.cr.o.cdc.lavayacoins.responses.SaveStoreSuccess
import graphql.kickstart.tools.SchemaParserDictionary
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class GrapqhqlConfiguration {

    @Bean
    fun schemaParserDictionary(): SchemaParserDictionary =
            SchemaParserDictionary()
                    .add(SaveStoreSuccess::class)
                    .add(SaveStoreResult::class)
                    .add(SaveStoreErrorInvalidAuthorities::class)
}
