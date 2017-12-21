package com.ibtikar.myfirstmvvmapplication.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class BooleanAsIntAdapter : TypeAdapter<Boolean>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Boolean?) {
        if (value == null) {
            out.nullValue()
        } else {
            out.value(value)
        }
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Boolean? {
        val peek = `in`.peek()
        when (peek) {
            JsonToken.BOOLEAN -> return `in`.nextBoolean()
            JsonToken.NULL -> {
                `in`.nextNull()
                return null
            }
            JsonToken.NUMBER -> return `in`.nextInt() != 0
            JsonToken.STRING -> return java.lang.Boolean.parseBoolean(`in`.nextString())
            else -> throw IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek)
        }
    }
}