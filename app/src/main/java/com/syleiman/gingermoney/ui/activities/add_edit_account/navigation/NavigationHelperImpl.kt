package com.syleiman.gingermoney.ui.activities.add_edit_account.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseImpl
import javax.inject.Inject

class NavigationHelperImpl
@Inject
constructor() : NavigationHelperBaseImpl(R.id.addEditAccountNavHostFragment), NavigationHelper {

    override fun setAddAccountAsHome(activity: FragmentActivity) = setHome(R.id.addAccountFragment, activity, null)

    override fun setEditAccountAsHome(activity: FragmentActivity, accountDbId: Long)  {

        val bundle = Bundle()
        bundle.putLong(NavigationArgs.ACCOUNT_DB_ID, accountDbId)

        setHome(R.id.editAccountFragment, activity, bundle)
    }

    private fun setHome(@IdRes id: Int, activity: FragmentActivity, destinationArgs: Bundle?) {
        val controller = getNavigationController(activity)
        val inflater = controller.navInflater
        val graph = inflater.inflate(R.navigation.activity_add_edit_account)
        graph.startDestination = id
        controller.setGraph(graph, destinationArgs)
    }

    override fun getTitle(activity: FragmentActivity) =
        getNavigationController(activity).currentDestination!!.label.toString()

    override fun processBackAnimation(activity: FragmentActivity) =
        activity.overridePendingTransition(R.anim.nav_slide_in_left, R.anim.nav_slide_out_right);
}