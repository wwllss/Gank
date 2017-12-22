package com.nsxz.gank.http

/**
 * @author zhangyuan
 * @date 2017/12/22.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Api(val path: String, val method: HttpMethod = HttpMethod.GET)