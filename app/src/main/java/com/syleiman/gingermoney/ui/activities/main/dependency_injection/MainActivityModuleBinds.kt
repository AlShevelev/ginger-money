package com.syleiman.gingermoney.ui.activities.main.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeaderLinkImpl
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeaderLink
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelperImpl
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelper
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModuleBinds {

    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelperImpl): NavigationHelper

    @Binds
    @ActivityScope
    abstract fun provideAccountsHeaderLink(bridge: AccountsHeaderLinkImpl): AccountsHeaderLink
}