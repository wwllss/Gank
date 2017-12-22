package com.nsxz.gank.api

import com.nsxz.gank.http.Api
import com.nsxz.gank.http.Req

/**
 * @author zhangyuan
 * @date 2017/12/22.
 */
@Api(path = "/data/{category}/{count}/{page}")
class GetTypeReq : Req() {

    lateinit var category: String

    var count = 15

    var page = 1

}