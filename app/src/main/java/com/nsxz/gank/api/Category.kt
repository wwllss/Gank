package com.nsxz.gank.api

/**
 * @author zhangyuan
 * @date 2017/12/22.
 */
class Category {

    lateinit var _id: String

    lateinit var createdAt: String

    lateinit var desc: String

    lateinit var publishedAt: String

    lateinit var source: String

    lateinit var type: String

    lateinit var url: String

    lateinit var who: String

    var used: Boolean = false

    lateinit var images: MutableList<String>

}