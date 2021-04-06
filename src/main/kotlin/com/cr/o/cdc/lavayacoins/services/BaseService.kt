package com.cr.o.cdc.lavayacoins.services

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

abstract class BaseService<T, ID>(private val repository: JpaRepository<T, ID>) {

    open fun save(entity: T, id: ID? = null): T? =
            if (id?.let { findById(it) } == null) {
                repository.save(entity)
            } else {
                null
            }

    open fun findById(id: ID): T? {
        val result = repository.findById(id)
        return if (result == Optional.empty<T>()) {
            null
        } else {
            result.get()
        }
    }

    open fun delete(id: ID): T? {
        val t = findById(id)
        return if (t != null) {
            repository.deleteById(id)
            t
        } else {
            null
        }
    }

    open fun saveAll(entities: List<T>): List<T> = repository.saveAll(entities)
}