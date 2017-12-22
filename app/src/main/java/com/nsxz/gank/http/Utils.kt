package com.nsxz.gank.http

import java.io.Closeable

/**
 * @author zhangyuan
 * @date 2017/12/21.
 */
object Utils {

    fun closeQuietly(cloneable: Closeable?){
        if (cloneable == null) return
        cloneable.close()
    }

}