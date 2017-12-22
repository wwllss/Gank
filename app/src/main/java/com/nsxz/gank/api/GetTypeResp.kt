package com.nsxz.gank.api

import com.nsxz.gank.http.Resp

/**
 * @author zhangyuan
 * @date 2017/12/22.
 */
class GetTypeResp : Resp() {

    lateinit var results: MutableList<Category>

}