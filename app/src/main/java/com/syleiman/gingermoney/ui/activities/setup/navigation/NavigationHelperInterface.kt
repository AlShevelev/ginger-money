package com.syleiman.gingermoney.ui.activities.setup.navigation

import androidx.fragment.app.Fragment
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseInterface

/**
 *
 */
interface NavigationHelperInterface: NavigationHelperBaseInterface {
    /**
     *
     */
    fun moveToNext(currentFragment: Fragment)
}