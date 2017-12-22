package com.nsxz.gank.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nsxz.gank.R
import com.nsxz.gank.api.Category
import com.nsxz.gank.api.GetTypeReq
import com.nsxz.gank.api.GetTypeResp
import com.nsxz.gank.constants.CategoryName
import com.nsxz.gank.http.Callback
import com.nsxz.gank.http.Client
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.item_type_data.view.*

/**
 * @author zhangyuan
 * @date 2017/12/22.
 */
class CategoryFragment : Fragment() {

    private val req = GetTypeReq().apply {
        category = CategoryName.ANDROID
    }

    private val dataList = mutableListOf<Category>()

    private val adapter = CategoryAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        refreshLayout.setOnRefreshListener {
            getData()
        }
        listView.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        listView.adapter = adapter
        refreshLayout.autoRefresh()
    }

    private fun getData() {
        Client.send(req, object : Callback<GetTypeResp> {
            override fun onSuccess(resp: GetTypeResp) {
                finishRefresh()
                dataList.addAll(resp.results)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(resp: GetTypeResp) {
                finishRefresh()
            }

            override fun onError(errCode: Int) {
                finishRefresh()
            }
        })
    }

    private fun finishRefresh() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadmore()
    }

    private inner class CategoryAdapter : RecyclerView.Adapter<CategoryHolder>() {
        override fun onBindViewHolder(holder: CategoryHolder?, position: Int) {
            val category = dataList[position]
            val images = category.images
            if (images != null && images.isNotEmpty()) {
                Glide.with(this@CategoryFragment).load(images[0]).into(holder?.itemView?.image)
                holder?.itemView?.image?.visibility = View.VISIBLE
            } else {
                holder?.itemView?.image?.visibility = View.GONE
            }
            holder?.itemView?.desc?.text = category.desc
            holder?.itemView?.provider?.text = category.who
            holder?.itemView?.time?.text = category.publishedAt
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryHolder {
            return CategoryHolder(LayoutInflater.from(activity).inflate(R.layout.item_type_data, parent, false))
        }

        override fun getItemCount(): Int = dataList.size


    }

    private inner class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        @JvmStatic
        fun newInstance(): CategoryFragment {
            return CategoryFragment()
        }
    }

}