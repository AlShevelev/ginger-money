package com.syleiman.gingermoney.ui.activities.add_edit_account.navigation

import androidx.fragment.app.FragmentActivity

interface NavigationHelperInterface {
    fun setAddAccountAsHome(activity: FragmentActivity)

    fun setEditAccountAsHome(activity: FragmentActivity)

    fun getTitle(activity: FragmentActivity): String

    fun processBackAnimation(activity: FragmentActivity)
}