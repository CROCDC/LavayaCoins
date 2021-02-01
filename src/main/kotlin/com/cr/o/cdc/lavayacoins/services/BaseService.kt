package com.cr.o.cdc.lavayacoins.services

import com.cr.o.cdc.lavayacoins.utils.value
import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseService<T, ID>(private val repository: JpaRepository<T, ID>) {

    open fun save(entity: T, id: ID) =
            if (findById(id) == null) {
                repository.save(entity)
            } else {
                null
            }

    open fun findById(id: ID): T? = repository.findById(id).value()
}