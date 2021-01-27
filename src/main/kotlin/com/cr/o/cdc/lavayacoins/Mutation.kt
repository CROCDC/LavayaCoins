package com.cr.o.cdc.lavayacoins

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.cr.o.cdc.lavayacoins.inputs.CreateUserInput
import com.cr.o.cdc.lavayacoins.inputs.LoginUserInput
import com.cr.o.cdc.lavayacoins.inputs.SaveStoreInput
import com.cr.o.cdc.lavayacoins.model.*
import com.cr.o.cdc.lavayacoins.repos.UserRepository
import com.cr.o.cdc.lavayacoins.utils.JWTToken
import org.springframework.stereotype.Component

@Component
class Mutation(val userRepository: UserRepository) : GraphQLMutationResolver {

    fun saveStore(storeInput: SaveStoreInput): Store? =
            if (JWTToken.validateJWTToken(storeInput.accessToken) != null) {
                Store("", "d", Coordinates(0f, 0f))
            } else {
                null
            }

    fun loginUser(loginUserInput: LoginUserInput): UserCredentials? =
            userRepository.findById(loginUserInput.username).get().let {
                UserCredentials(
                        it,
                        Credentials(
                                JWTToken.getJWTToken(it.username),
                                ""
                        )
                )
            }


    fun createUser(createUserInput: CreateUserInput): UserCredentials? = userRepository.save(
            CustomerUser(createUserInput.username, createUserInput.password)
    )?.let {
        UserCredentials(
                it,
                Credentials(
                        JWTToken.getJWTToken(it.username),
                        ""
                )
        )

    }

}