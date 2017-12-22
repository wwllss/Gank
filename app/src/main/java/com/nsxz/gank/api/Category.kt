package com.nsxz.gank.api

/**
 * @author zhangyuan
 * @date 2017/12/22.
 */
data class Category(
        val _id: String,

        val createdAt: String,

        val desc: String,

        val publishedAt: String,

        val source: String,

        val type: String,

        val url: String,

        val who: String,

        val used: Boolean,

        val images: MutableList<String>?
)