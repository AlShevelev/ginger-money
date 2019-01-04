package com.syleiman.gingermoney.ui.common.navigation

import androidx.fragment.app.Fragment

interface NavigationHelperBaseInterface {
    /**
     * Move back in back stack
     */
    fun moveBack(currentFragment: Fragment)
}