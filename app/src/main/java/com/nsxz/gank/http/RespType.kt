package com.nsxz.gank.http

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author zhangyuan
 * @date 2017/8/15.
 */
object RespType {
    fun typeOf(listener: Callback<*>): Type {
        val listenerClass: Class<out Callback<*>> = listener::class.java
        val genericInterfaces = listenerClass.genericInterfaces
        val parameterizeType = if (genericInterfaces == null || genericInterfaces.isEmpty()) {
            val genericSuperclass = listenerClass.genericSuperclass
            genericSuperclass as ParameterizedType
        } else {
            genericInterfaces[0] as ParameterizedType
        }

        val typeArguments = parameterizeType.actualTypeArguments
        return if (typeArguments.isNotEmpty()) {
            typeArguments[0]
        } else {
            EmptyData::class.java
        }
    }
}
