package com.nsxz.gank.util

import android.text.TextUtils

import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap

/**
 * 获取字段工具类.
 * Created by zhangyuan on 15/8/3.
 */
object FieldUtils {

    private val CACHE = mutableMapOf<Class<*>, Array<Field?>>()

    fun getFields(clazz: Class<*>): Array<Field?> {
        val cache = CACHE[clazz]
        if (cache != null)
            return cache

        val clazzName = clazz.name
        if (TextUtils.isEmpty(clazzName)
                || clazzName.startsWith("java.")
                || clazzName.startsWith("android.")
                || clazzName.startsWith("javax."))
            return arrayOfNulls(0)

        val superClass = clazz.superclass
        //递归调用 获取父类字段
        val superFields = getFields(superClass)
        val fields = ArrayList<Field?>()
        Collections.addAll(fields, *superFields)

        val selfFields = clazz.declaredFields
        for (field in selfFields) {
            field.isAccessible = true
            val modifiers = field.modifiers
            if (!Modifier.isStatic(modifiers)) {
                fields.add(field)
            }
        }
        val array = fields.toTypedArray()
        CACHE.put(clazz, array)
        return array
    }

}
