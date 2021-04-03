package com.cr.o.cdc.lavayacoins.services

import com.cr.o.cdc.lavayacoins.utils.value
import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseService<T, ID>(private val repository: JpaRepository<T, ID>) {

    open fun save(entity: T, id: ID? = null): T? =
            if (id?.let { findById(it) } == null) {
                repository.save(entity)
            } else {
                null
            }

    open fun findById(id: ID): T? = repository.findById(id).value()

    open fun delete(id: ID): T? {
        val store = findById(id)
        return if (store != null) {
            repository.deleteById(id)
            store
        } else {
            null
        }
    }

    open fun saveAll(entities: List<T>): List<T> = repository.saveAll(entities)
}