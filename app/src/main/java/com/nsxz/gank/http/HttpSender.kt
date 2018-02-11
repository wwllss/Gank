package com.nsxz.gank.http

import java.net.HttpURLConnection
import java.net.URL

/**
 * @author zhangyuan
 * @date 2017/12/21.
 */
internal class HttpSender {

    fun send(url: String, method: HttpMethod, params: String = ""): Result {
        val connection = openConnection(url, method)
        connection.connect()
        if (method == HttpMethod.POST && !params.isEmpty()) {
            val outputStream = connection.outputStream
            outputStream.write(params.toByteArray())
            outputStream.flush()
            outputStream.close()
        }
        val respCode = connection.responseCode
        val result = Result()
        result.errCode = respCode
        if (respCode == 200) {
            val sb = StringBuilder()
            val inputStream = connection.inputStream
            inputStream.reader().forEachLine {
                sb.append(it)
            }
            inputStream.close()
            result.content = sb.toString()
        }
        return result
    }

    private fun openConnection(urlStr: String, method: HttpMethod): HttpURLConnection {
        val url = URL(urlStr)
        val connection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = DEFAULT_TIMEOUT
        connection.readTimeout = DEFAULT_TIMEOUT
        connection.requestMethod = method.value
        connection.setRequestProperty("Connection", "Keep-Alive")
        connection.doOutput = method == HttpMethod.POST
        connection.doInput = true
        return connection
    }

    class Result {
        var errCode = -1
        var content = ""
    }

    companion object {
        const val DEFAULT_TIMEOUT = 10000
    }

}