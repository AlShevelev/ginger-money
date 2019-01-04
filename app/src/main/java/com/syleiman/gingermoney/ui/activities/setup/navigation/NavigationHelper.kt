package com.syleiman.gingermoney.ui.activities.setup.navigation

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.activities.setup.fragments.BaseCurrencyFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.MasterPasswordFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.ProtectionMethodFragment
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase
import javax.inject.Inject

/**
 *
 */
class NavigationHelper
@Inject
constructor() : NavigationHelperBase(R.id.navHostFragment), NavigationHelperInterface {
    /**
     *
     */
    override fun moveToBaseCurrency(currentFragment: MasterPasswordFragment) {
        moveTo(currentFragment, R.id.action_masterPasswordFragment_to_baseCurrencyFragment)
    }

    /**
     *
     */
    override fun moveToProtectionMethod(currentFragment: BaseCurrencyFragment) {
        moveTo(currentFragment, R.id.action_baseCurrencyFragment_to_protectionMethodFragment)
    }

    /**
     *
     */
    override fun moveToMainActivity(currentFragment: ProtectionMethodFragment) {
        moveTo(currentFragment, R.id.action_protectionMethodFragment_to_mainActivity, true)
    }
}