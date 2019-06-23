package com.syleiman.gingermoney.ui.common.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface NavigationHelperBase {
    /**
     * Move back in back stack
     */
    fun moveBack(currentFragment: Fragment, finishCurrentActivity: Boolean = false)

    /**
     * Move back in back stack
     */
    fun moveBack(currentActivity: FragmentActivity)
}