package com.nsxz.gank

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nsxz.gank.extension.screenWidth
import com.nsxz.gank.extension.toast
import kotlinx.android.synthetic.main.fragment_navigation.*

/**
 * @author zhangyuan
 * @date 2017/12/21.
 */
class NavigationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val option = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeResource(resources, R.mipmap.navigation_head, option)
        val bitmapWidth = option.outWidth
        val bitmapHeight = option.outHeight
        val width = activity.screenWidth().times(0.618)
        val headerView = navigationView.getHeaderView(0)
        headerView.layoutParams.height = (width * bitmapHeight / bitmapWidth).toInt()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_android -> activity.toast(it.title)
                R.id.menu_ios -> activity.toast(it.title)
                R.id.menu_front_end -> activity.toast(it.title)
                R.id.menu_app -> activity.toast(it.title)
                R.id.menu_recommend -> activity.toast(it.title)
                R.id.menu_expand_resource -> activity.toast(it.title)
                R.id.menu_rest_video -> activity.toast(it.title)
                R.id.menu_welfare -> activity.toast(it.title)
            }
            return@setNavigationItemSelectedListener true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): NavigationFragment {
            return NavigationFragment()
        }
    }

}