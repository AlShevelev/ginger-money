package com.syleiman.gingermoney.ui.activities.login.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface NavigationHelperInterface {
    /**
     * Set fragment with master-password as home
     */
    fun setMasterPasswordAsHome(activity: FragmentActivity)

    /**
     * Set fragment with fingerprint as home
     */
    fun setFingerprintAsHome(activity: FragmentActivity)

    /**
     * Switch between fragments
     */
    fun switch(currentFragment: Fragment)

    /**
     * Complete navigation and leave activity
     */
    fun complete(currentFragment: Fragment)
}