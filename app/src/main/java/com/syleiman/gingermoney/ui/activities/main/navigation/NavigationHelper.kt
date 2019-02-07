package com.syleiman.gingermoney.ui.activities.main.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase
import javax.inject.Inject

/**
 *
 */
class NavigationHelper
@Inject
constructor() : NavigationHelperBase(R.id.mainNavHostFragment), NavigationHelperInterface {

    private var onDestinationChangedListener: NavController.OnDestinationChangedListener? = null

    /**
     *
     */
    override fun setOnDestinationChangedListener(activity: FragmentActivity, listener: (NavDestination) -> Unit) {
        val controller = getNavigationController(activity)

        onDestinationChangedListener?.let { controller.removeOnDestinationChangedListener(it) }

        NavController.OnDestinationChangedListener { _, destination, _ -> listener(destination) }
            .apply {
                onDestinationChangedListener = this
                getNavigationController(activity).addOnDestinationChangedListener(this)
            }
    }

    /**
     *
     */
    override fun linkToBottomNavigation(activity: FragmentActivity, bottomNavigationView: BottomNavigationView) {
        NavigationUI.setupWithNavController(bottomNavigationView, getNavigationController(activity))
    }
}