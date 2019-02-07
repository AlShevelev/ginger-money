package com.syleiman.gingermoney.ui.activities.main.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDestination
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseInterface

/**
 *
 */
interface NavigationHelperInterface : NavigationHelperBaseInterface {

    /**
     *
     */
    fun setOnDestinationChangedListener(activity: FragmentActivity, listener: (NavDestination) -> Unit)

    /**
     *
     */
    fun linkToBottomNavigation(activity: FragmentActivity, bottomNavigationView: BottomNavigationView)
}