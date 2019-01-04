package com.syleiman.gingermoney.ui.activities.setup.navigation

import com.syleiman.gingermoney.ui.activities.setup.fragments.BaseCurrencyFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.MasterPasswordFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.ProtectionMethodFragment
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseInterface

/**
 *
 */
interface NavigationHelperInterface: NavigationHelperBaseInterface {
    /**
     *
     */
    fun moveToBaseCurrency(currentFragment: MasterPasswordFragment)

    /**
     *
     */
    fun moveToProtectionMethod(currentFragment: BaseCurrencyFragment)

    /**
     *
     */
    fun moveToMainActivity(currentFragment: ProtectionMethodFragment)
}