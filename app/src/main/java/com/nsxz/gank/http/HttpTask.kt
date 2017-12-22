package com.nsxz.gank.http

import android.os.AsyncTask
import android.text.TextUtils
import android.util.Log
import com.nsxz.gank.util.FieldUtils

/**
 * @author zhangyuan
 * @date 2017/12/21.
 */
internal class HttpTask<T : Resp>(private val callback: Callback<T>?) : AsyncTask<Req, Void, T>() {

    override fun doInBackground(vararg params: Req?): T? {
        val req = params[0]!!
        val reqInfo = getReqInfo(req)
        val result = HttpSender().send(processUrl(req, reqInfo.path), reqInfo.method, buildPostParams(req, reqInfo.method))
        val content = result.content
        if (!TextUtils.isEmpty(content)) {
            Log.i("Response", content)
            val resp: T = JsonMarshaller.fromJson(content, RespType.typeOf(callback!!))
            resp.errCode = result.errCode
            return resp
        }
        return null
    }

    private fun processUrl(req: Req, path: String): String {
        val fields = FieldUtils.getFields(req::class.java)
        var tempPath = path
        fields.forEach {
            it!!.isAccessible = true
            tempPath = tempPath.replace("{${it.name}}", it.get(req).toString())
        }
        return "http://gank.io/api$tempPath"
    }

    private fun buildPostParams(req: Req, method: HttpMethod): String {
        if (HttpMethod.GET == method) {
            return ""
        }
        val fields = FieldUtils.getFields(req::class.java)
        var params: String = ""
        fields.forEachIndexed { index, field ->
            field!!.isAccessible = true
            params += "${if (index > 0) "&" else ""}{${field.name}}=${field.get(req)}"
        }
        return params
    }

    fun request(req: Req) {
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, req)
    }

    private fun getReqInfo(req: Req): ReqInfo {
        val reqClass = req::class.java
        val cache = CACHE[reqClass]
        if (cache != null) {
            return cache
        }
        if (reqClass.isAnnotationPresent(Api::class.java)) {
            val api = reqClass.getAnnotation(Api::class.java)
            val reqInfo = ReqInfo()
            reqInfo.path = api.path
            reqInfo.method = api.method
            return reqInfo
        }
        throw IllegalArgumentException("must has annotate Api")
    }

    override fun onPostExecute(result: T?) {
        if (result != null && result.errCode == 200) {
            if (!result.error) {
                callback?.onSuccess(result)
            } else {
                callback?.onFailure(result)
            }
        } else {
            callback?.onError(result?.errCode ?: -1)
        }
    }

    companion object {
        private val CACHE = mutableMapOf<Class<*>, ReqInfo>()
    }

    private class ReqInfo {
        lateinit var path: String
        lateinit var method: HttpMethod
    }
}