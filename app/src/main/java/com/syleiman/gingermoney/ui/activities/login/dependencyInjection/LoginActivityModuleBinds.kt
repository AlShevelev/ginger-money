package com.syleiman.gingermoney.ui.activities.login.dependencyInjection

import com.syleiman.gingermoney.application.dependencyInjection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.model.MasterPasswordModel
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.model.MasterPasswordModelInterface
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
//
//    /**
//     *
//     */
//    @Binds
//    abstract fun provideFingerprintModel(model: FingerprintModel): FingerprintModelInterface
}