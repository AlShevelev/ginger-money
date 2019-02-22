package com.syleiman.gingermoney.ui.activities.main.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.main.MainActivity
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragment
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.AccountsViewModel
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view.SettingsFragment
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_model.SettingsViewModel
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeader
import com.syleiman.gingermoney.ui.activities.main.headers.settings.SettingsHeader
import dagger.Subcomponent

/**
 *
 */
@Subcomponent(modules = [MainActivityModuleBinds::class])
@ActivityScope
interface MainActivityComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): MainActivityComponent
    }

    fun inject(activity : MainActivity)

    fun inject(header : SettingsHeader)
    fun inject(fragment : SettingsFragment)
    fun inject(viewModel: SettingsViewModel)

    fun inject(header : AccountsHeader)
    fun inject(fragment : AccountsFragment)
    fun inject(viewModel: AccountsViewModel)
}