package com.syleiman.gingermoney.ui.common.navigation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation

/**
 * Wrapper around Navigation component
 * @param navHostId - id of Fragment when navigation is hosted
 */
abstract class NavigationHelperBase(@IdRes private val navHostId: Int) : NavigationHelperBaseInterface {
    /**
     * Move back in back stack
     */
    override fun moveBack(currentFragment: Fragment, finishCurrentActivity: Boolean) {
        if(finishCurrentActivity) {
            moveBack(currentFragment.activity!!)
        } else {
            getNavigationController(currentFragment).popBackStack()
        }
    }

    /**
     * Move back in back stack
     */
    override fun moveBack(currentActivity: FragmentActivity) = currentActivity.onBackPressed()

    /**
     * Move to some destination
     */
    protected fun moveTo(currentFragment: Fragment, destinationId: Int, finishCurrentActivity: Boolean = false, args: Bundle? = null) {
        getNavigationController(currentFragment).navigate(destinationId, args)

        if(finishCurrentActivity) {
            currentFragment.requireActivity().finish()
        }
    }

    protected fun getNavigationController(activity: FragmentActivity) = Navigation.findNavController(activity, navHostId)

    private fun getNavigationController(currentFragment: Fragment) = getNavigationController(currentFragment.requireActivity())
}