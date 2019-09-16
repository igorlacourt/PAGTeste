package com.lacourt.pagteste

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lacourt.pagteste.ui.dashboard.DashboardFragment
import com.lacourt.pagteste.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private var home: HomeFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.my_tool_bar)
        setSupportActionBar(toolbar);

        supportFragmentManager
            .beginTransaction()
            .add(R.id.nav_host_fragment, HomeFragment(), "home")
            .commit()

//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

    fun onDateDescClick(v: View) {
        val home = supportFragmentManager.findFragmentByTag("home")
        if (home != null)
            (home as HomeFragment)?.orderDateDesc()

    }

    fun onDateAscClick(v: View) {
        val home = supportFragmentManager.findFragmentByTag("home")
        if (home != null) {
            (home as HomeFragment)?.orderDateAsc()
        }
    }

    fun onHomeClick(view: View) {
        var homeFragment = supportFragmentManager.findFragmentByTag("home")

        if (homeFragment == null || !homeFragment.isAdded) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment(), "home")
                .commit()
        }

    }

    fun onMyListClick(view: View) {
        var myListFragment = supportFragmentManager.findFragmentByTag("my_list")

        if (myListFragment == null || !myListFragment.isAdded) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, DashboardFragment(), "my_list")
                .commit()
        }
    }
}
