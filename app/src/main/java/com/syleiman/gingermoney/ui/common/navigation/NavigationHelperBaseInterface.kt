package com.syleiman.gingermoney.ui.common.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface NavigationHelperBaseInterface {
    /**
     * Move back in back stack
     */
    fun moveBack(currentFragment: Fragment, finishCurrentActivity: Boolean = false)

    /**
     * Move back in back stack
     */
    fun moveBack(currentActivity: FragmentActivity)
}