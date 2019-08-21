package com.syleiman.gingermoney.ui.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderBase
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeader
import com.syleiman.gingermoney.ui.activities.main.headers.HeaderTags
import com.syleiman.gingermoney.ui.activities.main.headers.PaymentsHeader
import com.syleiman.gingermoney.ui.activities.main.headers.SettingsHeader
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)

        App.injections.get<MainActivityComponent>().inject(this)

        navigation.setOnDestinationChangedListener(this) { title, tag -> updateHeader(title, tag) }
        navigation.linkToBottomNavigation(this, mainNavPanel)
    }

    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) {
            App.injections.release<MainActivityComponent>()
        }
    }

    private fun updateHeader(title: CharSequence?, tag: String) {
        // Remove an old header
        mainToolbar.findViewWithTag<ConstraintLayout>(HeaderBase.CURRENT_HEADER_TAG)
            ?.let {
                (it as HeaderBase).detachFromFragment()
                mainToolbar.removeView(it)
            }

        // And add a new one
        when(tag) {
            HeaderTags.PAYMENTS_FRAGMENT -> PaymentsHeader.create(this, title, mainToolbar)
            HeaderTags.STATISTICS_FRAGMENT -> SettingsHeader.create(this, title, mainToolbar)
            HeaderTags.ACCOUNTS_FRAGMENT -> AccountsHeader.create(this, title, mainToolbar)
            HeaderTags.SETTINGS_FRAGMENT -> SettingsHeader.create(this, title, mainToolbar)
        }
    }
}
