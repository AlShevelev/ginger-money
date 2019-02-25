package com.syleiman.gingermoney.ui.activities.addEditAccount.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.addEditAccount.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.addEditAccount.navigation.NavigationHelperInterface
import dagger.Binds
import dagger.Module

@Module
abstract class AddEditAccountActivityModuleBinds {
    /**
     *
     */
    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelper): NavigationHelperInterface
}