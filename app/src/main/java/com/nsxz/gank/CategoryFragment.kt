package com.nsxz.gank

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsxz.gank.api.GetTypeReq
import com.nsxz.gank.api.GetTypeResp
import com.nsxz.gank.constants.CategoryName
import com.nsxz.gank.http.Callback
import com.nsxz.gank.http.Client
import com.nsxz.gank.http.JsonMarshaller
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * @author zhangyuan
 * @date 2017/12/22.
 */
class CategoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        sendButton.setOnClickListener {
            val req = GetTypeReq()
            req.category = CategoryName.ANDROID
            Client.send(req, object : Callback<GetTypeResp> {
                override fun onSuccess(resp: GetTypeResp) {
                    showButton.text = JsonMarshaller.formatJson(JsonMarshaller.toJson(resp))
                }

                override fun onFailure(resp: GetTypeResp) {
                    showButton.text = JsonMarshaller.toJson(resp)
                }

                override fun onError(errCode: Int) {
                    showButton.text = errCode.toString()
                }

            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): CategoryFragment {
            return CategoryFragment()
        }
    }

}