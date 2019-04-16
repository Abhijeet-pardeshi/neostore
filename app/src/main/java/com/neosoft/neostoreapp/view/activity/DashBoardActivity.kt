package com.neosoft.neostoreapp.view.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.utils.DrawerLocker
import com.neosoft.neostoreapp.view.fragment.DashboardFragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar_home.*

class DashBoardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, DrawerLocker {
    private var backStackChangeListener: FragmentManager.OnBackStackChangedListener =
        FragmentManager.OnBackStackChangedListener {
        }

    lateinit var toggle: ActionBarDrawerToggle
    private var isToolbarNavigationRegistered: Boolean = false
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportFragmentManager.beginTransaction().add(R.id.container,DashboardFragment()).commit()

        Log.d("Status", "onCreate")
        drawerLayout = findViewById(R.id.drawer_layout)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //switching between ham burger(drawer toggle and back button)
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                toggle.isDrawerIndicatorEnabled = false
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                toggle.setToolbarNavigationClickListener { onBackPressed() }
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                toggle.isDrawerIndicatorEnabled = true
                toggle.setToolbarNavigationClickListener { null }
            }
        }

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
//            if (getCurrentVisibleFragment() is DashboardFragment){
//                val dashboardFragment = getCurrentVisibleFragment() as DashboardFragment
//                dashboardFragment.autoSwipeImages()
//            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_search -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_tables -> {
                Toast.makeText(this@DashBoardActivity, "Show me Tables", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_orders -> {
                Toast.makeText(this@DashBoardActivity, "Show me Orders", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_account -> {

            }
            R.id.nav_cart -> {

            }
            R.id.nav_chairs -> {

            }
            R.id.nav_cupboards -> {

            }
            R.id.nav_logout -> {

            }
            R.id.nav_sofas -> {

            }
            R.id.nav_stores -> {

            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun setDrawerEnabled(enabled: Boolean) {
        if (!enabled) {
            Log.d("Enabled", "False")
            //you may want to open the drawer on swipe from the left
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            //remove toggle icon
            toggle.isDrawerIndicatorEnabled = false
            //show back button
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            if (!isToolbarNavigationRegistered) {
                toggle.setToolbarNavigationClickListener {
                    onBackPressed()
                }
            }

            this.isToolbarNavigationRegistered = true

        } else {
            Log.d("Enabled", "True")
//            drawerLayout = findViewById(R.id.drawer_layout)
//            //you must regain the power of swipe for the drawer
//            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
//            //remove the back button
//            supportActionBar?.setDisplayHomeAsUpEnabled(false)
//            //show toggle button again
//            toggle.isDrawerIndicatorEnabled = true
//            toggle.setToolbarNavigationClickListener { null }
//            toggle.syncState()
//            this.isToolbarNavigationRegistered = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        getSharedPreferences("MyPreferences", 0).edit().clear().apply()
    }


    private fun getCurrentVisibleFragment(): Fragment {
        val fragments = this.supportFragmentManager.fragments
        var visibleFragment: Fragment?= null
        fragments.forEach { fragment ->
            if (fragment != null && fragment.isVisible) {
                visibleFragment = fragment
            }
        }
        return visibleFragment!!
    }
}


//val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)
//
//        val lockMode = if (enabled) {
//            DrawerLayout.LOCK_MODE_UNLOCKED
//        } else {
//            DrawerLayout.LOCK_MODE_LOCKED_CLOSED
//        }
//
//        drawer_layout.setDrawerLockMode(lockMode)
//        toggle.isDrawerIndicatorEnabled = enabled
