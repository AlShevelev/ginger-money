package com.syleiman.gingermoney.ui.activities.main.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModelInterface
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeaderLink
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeaderLinkInterface
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelperInterface
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModuleBinds {

    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelper): NavigationHelperInterface

    @Binds
    abstract fun provideSettingsModel(model: SettingsModel): SettingsModelInterface

    @Binds
    abstract fun provideAccountsModel(model: AccountsModel): AccountsModelInterface

    @Binds
    @ActivityScope
    abstract fun provideAccountsHeaderLink(bridge: AccountsHeaderLink): AccountsHeaderLinkInterface
}