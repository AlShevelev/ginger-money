package com.syleiman.gingermoney.ui.activities.setup.navigation

import androidx.fragment.app.Fragment
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase

interface NavigationHelper: NavigationHelperBase {

    fun moveToNext(currentFragment: Fragment)
}