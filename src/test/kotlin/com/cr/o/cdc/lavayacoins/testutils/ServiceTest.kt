package com.cr.o.cdc.lavayacoins.testutils

import com.cr.o.cdc.lavayacoins.services.BaseService
import org.springframework.beans.factory.annotation.Autowired

abstract class ServiceTest<T : BaseService<*, *>> {
    @Autowired
    lateinit var service: T
}