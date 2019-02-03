package com.syleiman.gingermoney.ui.activities.login.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model.MasterPasswordModel
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelperInterface
import dagger.Binds
import dagger.Module

/**
 *
 */
@Module
abstract class LoginActivityModuleBinds {
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