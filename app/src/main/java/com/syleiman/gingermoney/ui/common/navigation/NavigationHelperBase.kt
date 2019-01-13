package com.syleiman.gingermoney.ui.common.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

/**
 * Wrapper around Navigation component
 * @param navHostId - id of Fragment when navigation is hosted
 */
abstract class NavigationHelperBase(@IdRes private val navHostId: Int) : NavigationHelperBaseInterface {
    /**
     * Move back in back stack
     */
    override fun moveBack(currentFragment: Fragment) {
        getNavigationController(currentFragment).popBackStack()
    }

    /**
     * Move to some destination
     */
    protected fun moveTo(currentFragment: Fragment, destinationId: Int, finishCurrentActivity: Boolean = false) {
        getNavigationController(currentFragment).navigate(destinationId)

        if(finishCurrentActivity) {
            currentFragment.requireActivity().finish()
        }
    }

    /**
     *
     */
    private fun getNavigationController(currentFragment: Fragment) =
        Navigation.findNavController(currentFragment.requireActivity(), navHostId)
}