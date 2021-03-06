package com.syleiman.gingermoney.ui.common.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.syleiman.gingermoney.R

/**
 * Wrapper around Navigation component
 * @param navHostId - id of Fragment when navigation is hosted
 */
abstract class NavigationHelperBaseImpl(@IdRes private val navHostId: Int) : NavigationHelperBase {
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

    override fun getTitle(activity: FragmentActivity) =
        getNavigationController(activity).currentDestination!!.label.toString()

    override fun processBackAnimation(activity: FragmentActivity) =
        activity.overridePendingTransition(R.anim.nav_slide_in_left, R.anim.nav_slide_out_right)

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

    protected fun getNavigationController(currentFragment: Fragment) = getNavigationController(currentFragment.requireActivity())

    protected fun setHome(
        @IdRes fragmentId: Int,
        @NavigationRes navigationGraphId: Int,
        activity: FragmentActivity,
        destinationArgs: Bundle?) {

        val controller = getNavigationController(activity)
        val inflater = controller.navInflater
        val graph = inflater.inflate(navigationGraphId)
        graph.startDestination = fragmentId
        controller.setGraph(graph, destinationArgs)
    }
}