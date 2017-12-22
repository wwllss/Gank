package com.nsxz.gank.http

/**
 * @author zhangyuan
 * @date 2017/12/21.
 */
object Client {

    fun <T : Resp> send(req: Req, callback: Callback<T>? = null) {
        HttpTask(callback).request(req)
    }

}