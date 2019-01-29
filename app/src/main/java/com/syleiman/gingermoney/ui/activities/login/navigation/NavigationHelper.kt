package com.syleiman.gingermoney.ui.activities.login.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.view.FingerprintFragment
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.view.MasterPasswordFragment
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase
import javax.inject.Inject

/**
 *
 */
class NavigationHelper
@Inject
constructor() : NavigationHelperBase(R.id.loginNavHostFragment), NavigationHelperInterface {
    /**
     * Set fragment with master-password as home
     */
    override fun setMasterPasswordAsHome(activity: FragmentActivity) = setHome(R.id.loginMasterPasswordFragment, activity)

    /**
     * Set fragment with fingerprint as home
     */
    override fun setFingerprintAsHome(activity: FragmentActivity) = setHome(R.id.fingerprintFragment, activity)

    /**
     * Switch between fragments
     */
    override fun switch(currentFragment: Fragment) {
        when(currentFragment) {
            is MasterPasswordFragment -> moveTo(currentFragment, R.id.action_masterPasswordFragment2_to_fingerprintFragment)
            is FingerprintFragment -> moveTo(currentFragment, R.id.action_fingerprintFragment_to_masterPasswordFragment2)
        }
    }

    /**
     * Complete navigation and leave activity
     */
    override fun complete(currentFragment: Fragment) {
        when(currentFragment) {
            is MasterPasswordFragment -> moveTo(currentFragment, R.id.action_masterPasswordFragment2_to_mainActivity2, true)
            is FingerprintFragment -> moveTo(currentFragment, R.id.action_fingerprintFragment_to_mainActivity2, true)
        }
    }

    /**
     *
     */
    private fun setHome(@IdRes id: Int, activity: FragmentActivity) {
        val controller = getNavigationController(activity)
        val inflater = controller.navInflater
        val graph = inflater.inflate(R.navigation.activity_login)
        graph.startDestination = id
        controller.graph = graph
    }
}