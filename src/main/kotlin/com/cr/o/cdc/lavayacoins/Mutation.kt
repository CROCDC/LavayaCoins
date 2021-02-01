package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.db.CustomerUser
import com.cr.o.cdc.lavayacoins.db.Store
import com.cr.o.cdc.lavayacoins.inputs.CreateCustomerUserInput
import com.cr.o.cdc.lavayacoins.inputs.LoginAdminUserInput
import com.cr.o.cdc.lavayacoins.inputs.LoginCustomerUserInput
import com.cr.o.cdc.lavayacoins.inputs.SaveStoreInput
import com.cr.o.cdc.lavayacoins.repos.StoreRepository
import com.cr.o.cdc.lavayacoins.responses.*
import com.cr.o.cdc.lavayacoins.services.CustomerUserService
import com.cr.o.cdc.lavayacoins.utils.Authority
import com.cr.o.cdc.lavayacoins.utils.JWTToken
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component

@Component
class Mutation(
        val userService: CustomerUserService,
        val storeRepository: StoreRepository
) : GraphQLMutationResolver {

    fun saveStore(storeInput: SaveStoreInput): SaveStoreResult {
        val neededAuthorities = listOf(Authority.ADMIN_STORES)
        return if (JWTToken.validateJWTToken(storeInput.accessToken, neededAuthorities) != null) {
            SaveStoreSuccess(storeRepository.save(Store("", storeInput.name, storeInput.location.toCoordinates())))
        } else {
            SaveStoreErrorInvalidAuthorities(
                    neededAuthorities,
                    JWTToken.getAuthorities(storeInput.accessToken)

            )
        }
    }

    fun loginCustomerUser(loginCustomerUserInput: LoginCustomerUserInput): CustomerUserCredentials? =
            userService.findById(loginCustomerUserInput.username)
                    ?.takeIf { it.password == loginCustomerUserInput.password }
                    ?.let {
                        CustomerUserCredentials(
                                it,
                                Credentials(
                                        JWTToken.getJWTToken(
                                                it.username,
                                                listOf(Authority.SEND_TIPS)
                                        ),
                                        ""
                                )
                        )
                    }

    fun loginAdminUser(loginAdminUserInput: LoginAdminUserInput): AdminUserCredentials? =
            userService.findById(loginAdminUserInput.username)
                    ?.takeIf { it.password == loginAdminUserInput.password }
                    ?.let {
                        AdminUserCredentials(
                                it,
                                Credentials(
                                        JWTToken.getJWTToken(
                                                it.username,
                                                listOf(Authority.ADMIN_STORES)
                                        ),
                                        ""
                                )
                        )
                    }


    fun createAdminUser(createAdminUserInput: CreateCustomerUserInput): AdminUserCredentials? = userService.save(
            CustomerUser(createAdminUserInput.username, createAdminUserInput.password),
            createAdminUserInput.username
    )?.let {
        AdminUserCredentials(
                it,
                Credentials(
                        JWTToken.getJWTToken(
                                it.username,
                                listOf(Authority.ADMIN_STORES)
                        ),
                        ""
                )
        )

    }

    fun createCustomerUser(createCustomerUserInput: CreateCustomerUserInput): CustomerUserCredentials? =
            userService.save(
                    CustomerUser(createCustomerUserInput.username, createCustomerUserInput.password),
                    createCustomerUserInput.username
            )?.let {
                CustomerUserCredentials(
                        it,
                        Credentials(
                                JWTToken.getJWTToken(
                                        it.username,
                                        listOf(Authority.SEND_TIPS)
                                ),
                                ""
                        )
                )

            }

}