package com.nsxz.gank

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import com.nsxz.gank.extension.replaceFragment
import com.nsxz.gank.extension.screenWidth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
        toolBar.setNavigationIcon(R.drawable.ic_menu_black_24dp)
        toolBar.setNavigationOnClickListener {
            val gravity = GravityCompat.START
            if (drawerLayout.isDrawerOpen(gravity)) {
                drawerLayout.closeDrawer(gravity)
            } else {
                drawerLayout.openDrawer(gravity)
            }
        }
        setNavigationView()
        replaceFragment(R.id.contentContainer, CategoryFragment.newInstance(), "CategoryFragment")
    }

    private fun setNavigationView() {
        val param = navigationContainer.layoutParams as DrawerLayout.LayoutParams
        param.width = (screenWidth() * 0.618).toInt()
        replaceFragment(R.id.navigationContainer, NavigationFragment.newInstance(), "NavigationView")
    }
}
