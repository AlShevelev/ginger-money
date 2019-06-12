package com.syleiman.gingermoney.ui.activities.add_edit_account.navigation

import androidx.fragment.app.FragmentActivity
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseInterface

interface NavigationHelperInterface: NavigationHelperBaseInterface {

    fun setAddAccountAsHome(activity: FragmentActivity)

    fun setEditAccountAsHome(activity: FragmentActivity, accountDbId: Long)

    fun getTitle(activity: FragmentActivity): String

    fun processBackAnimation(activity: FragmentActivity)
}