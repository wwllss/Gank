package com.nsxz.gank.http

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.lang.reflect.Type

/**
 * @author zhangyuan
 * @date 2017/12/21.
 */
object JsonMarshaller {

    private val G_SON = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .serializeSpecialFloatingPointValues()
            .create()

    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return G_SON.fromJson(json, clazz)
    }

    fun <T> fromJson(json: String, type: Type): T {
        return G_SON.fromJson(json, type)
    }

    fun toJson(any: Any): String {
        return G_SON.toJson(any)
    }

    fun formatJson(json: String): String {
        val jsonParser = JsonParser()
        val jsonElement = jsonParser.parse(json)
        return GsonBuilder().setPrettyPrinting().create().toJson(jsonElement)
    }

}