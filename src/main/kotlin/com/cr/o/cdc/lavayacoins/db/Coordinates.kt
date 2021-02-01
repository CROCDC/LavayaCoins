package com.cr.o.cdc.lavayacoins.db

import javax.persistence.Embeddable

@Embeddable
data class Coordinates(val latitude: Float, val longitude: Float)
