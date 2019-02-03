package com.syleiman.gingermoney.ui.activities.setup.navigation

import androidx.fragment.app.Fragment
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.view.BaseCurrencyFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.view.MasterPasswordFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.view.ProtectionMethodFragment
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase
import javax.inject.Inject

/**
 *
 */
class NavigationHelper
@Inject
constructor() : NavigationHelperBase(R.id.setupNavHostFragment), NavigationHelperInterface {
    /**
     *
     */
    override fun moveToNext(currentFragment: Fragment) {
        when(currentFragment) {
            is MasterPasswordFragment -> moveTo(currentFragment, R.id.action_masterPasswordFragment_to_baseCurrencyFragment)
            is BaseCurrencyFragment -> moveTo(currentFragment, R.id.action_baseCurrencyFragment_to_protectionMethodFragment)
            is ProtectionMethodFragment -> moveTo(currentFragment, R.id.action_protectionMethodFragment_to_mainActivity, true)
        }
    }
}