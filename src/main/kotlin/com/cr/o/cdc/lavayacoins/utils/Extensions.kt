package com.cr.o.cdc.lavayacoins.utils

import java.util.*

fun <T> Optional<T>.value(): T? = takeIf { !isEmpty }?.get()