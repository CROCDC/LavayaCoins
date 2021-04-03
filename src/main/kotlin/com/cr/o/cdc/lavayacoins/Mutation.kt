package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.db.AdminUser
import com.cr.o.cdc.lavayacoins.db.CustomerUser
import com.cr.o.cdc.lavayacoins.db.Store
import com.cr.o.cdc.lavayacoins.inputs.*
import com.cr.o.cdc.lavayacoins.responses.*
import com.cr.o.cdc.lavayacoins.services.AdminUserService
import com.cr.o.cdc.lavayacoins.services.CustomerUserService
import com.cr.o.cdc.lavayacoins.services.StoreService
import com.cr.o.cdc.lavayacoins.utils.Authority
import com.cr.o.cdc.lavayacoins.utils.JWTToken
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component

@Component
class Mutation(
        val userService: CustomerUserService,
        val adminUserService: AdminUserService,
        val storeService: StoreService
) : GraphQLMutationResolver {

    fun saveStore(storeInput: SaveStoreInput): SaveStoreResult {
        val neededAuthorities = listOf(Authority.ADMIN_STORES)
        return if (JWTToken.validateJWTToken(storeInput.accessToken, neededAuthorities) != null) {
            val store = storeInput.store
            SaveStoreSuccess(storeService.save(Store(store.name, store.coordinates.toCoordinates()))!!)
        } else {
            SaveStoreErrorInvalidAuthorities(
                    neededAuthorities,
                    JWTToken.getAuthorities(storeInput.accessToken)

            )
        }
    }

    fun loginCustomerUser(loginCustomerUserInput: LoginCustomerUserInput): LoginCustomerResult {
        val customerUser = userService.findById(loginCustomerUserInput.username)
        return when {
            customerUser != null && customerUser.password == loginCustomerUserInput.password ->
                LoginCustomerSuccess(
                        customerUser,
                        Credentials(
                                JWTToken.getJWTToken(
                                        customerUser.username,
                                        listOf(Authority.SEND_TIPS)
                                ),
                                ""
                        )
                )
            customerUser != null && customerUser.password != loginCustomerUserInput.password ->
                LoginCustomerError(LoginCustomerErrorCause.PASSWORD_MISMATCH)
            customerUser == null -> LoginCustomerError(LoginCustomerErrorCause.USER_NOT_EXIST)
            else -> LoginCustomerError(LoginCustomerErrorCause.UNKNOWN)
        }
    }

    fun loginAdminUser(loginAdminUserInput: LoginAdminUserInput): LoginAdminResult? {
        val adminUser = adminUserService.findById(loginAdminUserInput.username)
        return when {
            adminUser != null && loginAdminUserInput.password == adminUser.password -> LoginAdminSuccess(
                    adminUser,
                    Credentials(
                            JWTToken.getJWTToken(
                                    adminUser.username,
                                    adminUser.authorities
                            ),
                            ""
                    )
            )
            adminUser != null &&
                    loginAdminUserInput.password != adminUser.password ->
                LoginAdminError(LoginAdminErrorCause.PASSWORD_MISMATCH)
            adminUser == null -> LoginAdminError(LoginAdminErrorCause.USER_NOT_EXIST)
            else -> LoginAdminError(LoginAdminErrorCause.UNKNOWN)
        }
    }


    fun createAdminUser(createAdminUserInput: CreateAdminUserInput): CreateAdminResult =
            if (JWTToken.validateJWTToken(createAdminUserInput.accessToken, listOf(Authority.CREATE_ADMINS)) != null) {
                adminUserService.save(
                        AdminUser(
                                createAdminUserInput.username,
                                createAdminUserInput.password,
                                createAdminUserInput.authorities
                        ),
                        createAdminUserInput.username
                )?.let {
                    CreateAdminSuccess(
                            it,
                            Credentials(
                                    JWTToken.getJWTToken(
                                            it.username,
                                            createAdminUserInput.authorities
                                    ),
                                    ""
                            )
                    )

                } ?: CreateAdminError(CreateAdminErrorCause.USER_ALREADY_EXIST)
            } else {
                CreateAdminError(CreateAdminErrorCause.INVALID_AUTHORITIES)
            }


    fun createCustomerUser(createCustomerUserInput: CreateCustomerUserInput): CreateCustomerResult =
            userService.save(
                    CustomerUser(createCustomerUserInput.username, createCustomerUserInput.password),
                    createCustomerUserInput.username
            )?.let {
                CreateCustomerSuccess(
                        it,
                        Credentials(
                                JWTToken.getJWTToken(
                                        it.username,
                                        listOf(Authority.SEND_TIPS)
                                ),
                                ""
                        )
                )

            } ?: CreateCustomerError(CreateCustomerErrorCause.USER_ALREADY_EXIST)

    fun saveStores(saveAllStoresInput: SaveAllStoresInput): SaveStoresResult {
        val neededAuthorities = listOf(Authority.ADMIN_STORES)
        return if (JWTToken.validateJWTToken(saveAllStoresInput.accessToken, neededAuthorities) != null) {
            SaveStoresSuccess(
                    storeService.saveAll(
                            saveAllStoresInput.stores.map {
                                Store(
                                        it.name,
                                        it.coordinates.toCoordinates()
                                )
                            }
                    )
            )
        } else {
            SaveStoreErrorInvalidAuthorities(
                    neededAuthorities,
                    JWTToken.getAuthorities(saveAllStoresInput.accessToken)
            )
        }
    }

    fun deleteStoreById(deleteStoreByIdInput: DeleteStoreByIdInput): DeleteStoreResult {
        val neededAuthorities = listOf(Authority.ADMIN_STORES)
        return if (JWTToken.validateJWTToken(deleteStoreByIdInput.accessToken, neededAuthorities) != null) {
            val store = storeService.delete(deleteStoreByIdInput.id)
            if (store != null) {
                DeleteStoreSuccess(store)
            } else {
                DeleteStoreNotExist(deleteStoreByIdInput.id)
            }
        } else {
            DeleteStoreInvalidAuthorities(
                    neededAuthorities,
                    JWTToken.getAuthorities(deleteStoreByIdInput.accessToken)
            )
        }

    }


}