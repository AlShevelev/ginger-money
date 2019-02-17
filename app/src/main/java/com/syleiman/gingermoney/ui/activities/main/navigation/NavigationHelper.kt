package com.syleiman.gingermoney.ui.activities.main.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.activities.main.headers.HeaderTags
import com.syleiman.gingermoney.ui.common.navigation.NavigationHelperBase
import javax.inject.Inject

/**
 *
 */
class NavigationHelper
@Inject
constructor() : NavigationHelperBase(R.id.mainNavHostFragment), NavigationHelperInterface {

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
                    R.id.expensesFragment -> HeaderTags.EXPENSES_FRAGMENT
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

    /**
     *
     */
    override fun linkToBottomNavigation(activity: FragmentActivity, bottomNavigationView: BottomNavigationView) {
        NavigationUI.setupWithNavController(bottomNavigationView, getNavigationController(activity))
    }
}