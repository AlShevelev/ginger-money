package com.syleiman.gingermoney.ui.activities.main.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.main.MainActivity
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection.AccountsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.dependency_injection.SettingsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeader
import com.syleiman.gingermoney.ui.activities.main.headers.settings.SettingsHeader
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModuleBinds::class, MainActivityModuleChilds::class])
@ActivityScope
interface MainActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainActivityComponent
    }

    val accountsFragment: AccountsFragmentComponent.Builder

    val settingsFragment: SettingsFragmentComponent.Builder

    fun inject(activity : MainActivity)

    fun inject(header : SettingsHeader)

    fun inject(header : AccountsHeader)
}