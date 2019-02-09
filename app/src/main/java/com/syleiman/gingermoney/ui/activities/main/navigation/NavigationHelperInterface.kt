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
     * @param listener Listener to set. First parameter is a label; second is a destination point tag
     */
    fun setOnDestinationChangedListener(activity: FragmentActivity, listener: (CharSequence?, String) -> Unit)

    /**
     *
     */
    fun linkToBottomNavigation(activity: FragmentActivity, bottomNavigationView: BottomNavigationView)
}