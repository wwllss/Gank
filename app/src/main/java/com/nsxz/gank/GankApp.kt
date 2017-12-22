package com.nsxz.gank

import android.app.Application
import android.util.TypedValue
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * Created by zhangyuan on 2017/12/23.
 */
class GankApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, _ ->
            val header = ClassicsHeader(context)
            header.setTextSizeTitle(TypedValue.COMPLEX_UNIT_SP, 12.0F)
            header.setEnableLastTime(false)
            header.setDrawableArrowSize(12.0F)
            header.setDrawableProgressSize(12.0F)
            return@setDefaultRefreshHeaderCreater header
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreater { context, _ ->
            val footer = ClassicsFooter(context)
            footer.setTextSizeTitle(TypedValue.COMPLEX_UNIT_SP, 12.0F)
            footer.setDrawableSize(12.0F)
            footer.setDrawableProgressSize(12.0F)
            return@setDefaultRefreshFooterCreater footer
        }
    }

}