package com.cr.o.cdc.lavayacoins.inputs

import com.cr.o.cdc.lavayacoins.db.Coordinates

data class CoordinatesInput(val latitude: Float, val longitude: Float) {

    fun toCoordinates(): Coordinates = Coordinates(latitude, longitude)

}
