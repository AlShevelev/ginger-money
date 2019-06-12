package com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.add_edit_account.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.add_edit_account.navigation.NavigationHelperInterface
import dagger.Binds
import dagger.Module

@Module
abstract class AddEditAccountActivityModuleBinds {

    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelper): NavigationHelperInterface
}