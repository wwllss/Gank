package com.nsxz.gank.http

/**
 * @author zhangyuan
 * @date 2017/12/21.
 */
interface Callback<in T : Resp> {

    fun onSuccess(resp: T)

    fun onFailure(resp: T)

    fun onError(errCode: Int)

}