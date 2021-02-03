package com.cr.o.cdc.lavayacoins

import com.cr.o.cdc.lavayacoins.db.AdminAuthority
import com.cr.o.cdc.lavayacoins.db.AdminUser
import com.cr.o.cdc.lavayacoins.db.CustomerUser
import com.cr.o.cdc.lavayacoins.db.Store
import com.cr.o.cdc.lavayacoins.inputs.*
import com.cr.o.cdc.lavayacoins.repos.StoreRepository
import com.cr.o.cdc.lavayacoins.responses.*
import com.cr.o.cdc.lavayacoins.services.AdminUserService
import com.cr.o.cdc.lavayacoins.services.CustomerUserService
import com.cr.o.cdc.lavayacoins.utils.Authority
import com.cr.o.cdc.lavayacoins.utils.JWTToken
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component

@Component
class Mutation(
        val userService: CustomerUserService,
        val adminUserService: AdminUserService,
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

    fun loginAdminUser(loginAdminUserInput: LoginAdminUserInput): LoginAdminResult? {
        val adminUser = adminUserService.findById(loginAdminUserInput.username)
        return when {
            adminUser != null && loginAdminUserInput.password == adminUser.password -> LoginAdminSuccess(
                    adminUser,
                    Credentials(
                            JWTToken.getJWTToken(
                                    adminUser.username,
                                    adminUser.adminAuthorities.map {
                                        it.authority
                                    }
                            ),
                            ""
                    )
            )
            adminUser != null &&
                    loginAdminUserInput.password != adminUser.password ->
                LoginAdminUserError(LoginAdminErrorCause.PASSWORD_MISMATCH)
            adminUser == null -> LoginAdminUserError(LoginAdminErrorCause.USER_NOT_EXIST)
            else -> LoginAdminUserError(LoginAdminErrorCause.UNKNOWN)
        }
    }


    fun createAdminUser(createAdminUserInput: CreateAdminUserInput): CreateAdminResult = if (JWTToken.validateJWTToken(createAdminUserInput.accessToken, listOf(Authority.CREATE_ADMINS)) != null) {
        adminUserService.save(
                AdminUser(
                        createAdminUserInput.username,
                        createAdminUserInput.password,
                        createAdminUserInput.authorities.map {
                            AdminAuthority(
                                    it,
                                    "",
                                    null
                            )
                        }
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

        } ?: CreateAdminUserError(CreateAdminErrorCause.USER_ALREADY_EXIST)
    } else {
        CreateAdminUserError(CreateAdminErrorCause.INVALID_AUTHORITIES)
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