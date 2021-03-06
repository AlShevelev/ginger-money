package com.syleiman.gingermoney.ui.activities.main.navigation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragment
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.PaymentsFragment
import com.syleiman.gingermoney.ui.activities.main.headers.HeaderTags
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ACTION
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.DB_ID
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.ADD
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs.EDIT
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBaseImpl
import javax.inject.Inject

class NavigationHelperImpl
@Inject
constructor() : NavigationHelperBaseImpl(R.id.mainNavHostFragment), NavigationHelper {
    private var onDestinationChangedListener: NavController.OnDestinationChangedListener? = null

    /**
     * @param listener Listener to set. First parameter is a label; second is a destination point tag
     */
    override fun setOnDestinationChangedListener(activity: FragmentActivity, listener: (CharSequence?, String) -> Unit) {
        val controller = getNavigationController(activity)

        onDestinationChangedListener?.let { controller.removeOnDestinationChangedListener(it) }

        NavController
            .OnDestinationChangedListener { _, destination, _ ->
                val tag = when(destination.id) {
                    R.id.paymentsFragment -> HeaderTags.PAYMENTS_FRAGMENT
                    R.id.statisticsFragment -> HeaderTags.STATISTICS_FRAGMENT
                    R.id.accountsFragment -> HeaderTags.ACCOUNTS_FRAGMENT
                    R.id.settingsFragment -> HeaderTags.SETTINGS_FRAGMENT
                    else -> throw UnsupportedOperationException("Unknown Id: ${destination.id}")
                }

                listener(destination.label, tag)
            }
            .apply {
                onDestinationChangedListener = this
                getNavigationController(activity).addOnDestinationChangedListener(this)
            }
    }

    override fun linkToBottomNavigation(activity: FragmentActivity, bottomNavigationView: BottomNavigationView) {
        NavigationUI.setupWithNavController(bottomNavigationView, getNavigationController(activity))
    }

    /**
     * Move to add/edit account screen
     */
    override fun moveToAddAccount(currentFragment: AccountsFragment) {
        val args = Bundle().also { it.putString(ACTION, ADD) }
        moveTo(currentFragment, R.id.action_accountsFragment_to_addEditAccountActivity, args = args)
    }

    /**
     * Move to "Edit account" screen
     */
    override fun moveToEditAccount(currentFragment: AccountsFragment, accountDbId: Long) {
        val args = Bundle()
            .also {
                it.putString(ACTION, EDIT)
                it.putLong(DB_ID, accountDbId)
            }

        moveTo(currentFragment, R.id.action_accountsFragment_to_addEditAccountActivity, args = args)
    }

    override fun moveToAddPayment(currentFragment: PaymentsFragment) {
        val args = Bundle().also { it.putString(ACTION, ADD) }
        moveTo(currentFragment, R.id.action_paymentsFragment_to_addEditPaymentActivity, args = args)
    }
}