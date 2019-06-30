package com.syleiman.gingermoney.ui.activities.main.navigation

import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragment
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.PaymentsFragment
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase

interface NavigationHelper : NavigationHelperBase {

    /**
     * @param listener Listener to set. First parameter is a label; second is a destination point tag
     */
    fun setOnDestinationChangedListener(activity: FragmentActivity, listener: (CharSequence?, String) -> Unit)


    fun linkToBottomNavigation(activity: FragmentActivity, bottomNavigationView: BottomNavigationView)

    /**
     * Move to "Add account" screen
     */
    fun moveToAddAccount(currentFragment: AccountsFragment)

    /**
     * Move to "Edit account" screen
     */
    fun moveToEditAccount(currentFragment: AccountsFragment, accountDbId: Long)

    fun moveToAddPayment(currentFragment: PaymentsFragment)
}