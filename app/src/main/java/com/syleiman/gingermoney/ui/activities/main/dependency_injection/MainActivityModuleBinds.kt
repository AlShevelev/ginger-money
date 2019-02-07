package com.syleiman.gingermoney.ui.activities.main.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelperInterface
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModuleBinds {
    /**
     *
     */
    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelper): NavigationHelperInterface
}