package com.cr.o.cdc.lavayacoins.converters

import com.cr.o.cdc.lavayacoins.utils.Authority
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.persistence.AttributeConverter

class AuthoritiesConverter : AttributeConverter<List<Authority>, String> {

    override fun convertToDatabaseColumn(attribute: List<Authority>?): String =
            Gson().toJson(attribute ?: listOf<Authority>())

    override fun convertToEntityAttribute(dbData: String?): List<Authority> =
            if (dbData != null) {
                Gson().fromJson(
                        dbData,
                        object : TypeToken<List<Authority>>() {}.type
                )
            } else {
                listOf()
            }


}
