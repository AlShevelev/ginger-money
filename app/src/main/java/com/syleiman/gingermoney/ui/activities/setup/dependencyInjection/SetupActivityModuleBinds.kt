package com.syleiman.gingermoney.ui.activities.setup.dependencyInjection

import com.syleiman.gingermoney.application.dependencyInjection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.model.MasterPasswordModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import dagger.Binds
import dagger.Module

/**
 *
 */
@Module
abstract class SetupActivityModuleBinds {
    /**
     *
     */
    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelper): NavigationHelperInterface

    /**
     *
     */
    @Binds
    abstract fun provideMasterPasswordModel(model: MasterPasswordModel): MasterPasswordModelInterface
}