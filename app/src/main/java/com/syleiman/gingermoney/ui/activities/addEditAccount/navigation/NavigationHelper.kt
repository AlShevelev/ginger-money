package com.syleiman.gingermoney.ui.activities.addEditAccount.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase
import javax.inject.Inject

/**
 *
 */
class NavigationHelper
@Inject
constructor() : NavigationHelperBase(R.id.addEditAccountNavHostFragment), NavigationHelperInterface {

    /**
     *
     */
    override fun setAddAccountAsHome(activity: FragmentActivity) = setHome(R.id.addAccountFragment, activity)

    /**
     *
     */
    override fun setEditAccountAsHome(activity: FragmentActivity)  = setHome(R.id.editAccountFragment, activity)

    /**
     *
     */
    private fun setHome(@IdRes id: Int, activity: FragmentActivity) {
        val controller = getNavigationController(activity)
        val inflater = controller.navInflater
        val graph = inflater.inflate(R.navigation.activity_add_edit_account)
        graph.startDestination = id
        controller.graph = graph
    }

    /**
     *
     */
    override fun getTitle(activity: FragmentActivity) =
        getNavigationController(activity).currentDestination!!.label.toString()

    /**
     *
     */
    override fun processBackAnimation(activity: FragmentActivity) =
        activity.overridePendingTransition(R.anim.nav_slide_in_left, R.anim.nav_slide_out_right);
}