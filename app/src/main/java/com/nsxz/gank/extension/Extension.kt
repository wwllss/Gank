package com.nsxz.gank.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.Toast

/**
 * @author zhangyuan
 * @date 2017/12/21.
 */
fun Context.screenWidth(): Int {
    return resources.displayMetrics.widthPixels
}

fun Context.toast(any: Any) {
    Toast.makeText(this, any.toString(), Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(any: Any) {
    activity?.toast(any)
}

fun FragmentActivity.replaceFragment(containerViewId: Int, fragment: Fragment, tag: String? = null): Int {
    return supportFragmentManager.beginTransaction()
            .replace(containerViewId, fragment, tag)
            .commitAllowingStateLoss()
}

fun Fragment.replaceFragment(containerViewId: Int, fragment: Fragment, tag: String? = null): Int {
    return childFragmentManager.beginTransaction()
            .replace(containerViewId, fragment, tag)
            .commitAllowingStateLoss()
}