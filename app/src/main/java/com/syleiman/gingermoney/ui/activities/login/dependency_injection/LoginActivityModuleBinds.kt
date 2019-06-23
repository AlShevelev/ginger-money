package com.syleiman.gingermoney.ui.activities.login.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model.MasterPasswordModelImpl
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model.MasterPasswordModel
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelperImpl
import com.syleiman.gingermoney.ui.activities.login.navigation.NavigationHelper
import dagger.Binds
import dagger.Module

@Module
abstract class LoginActivityModuleBinds {

    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelperImpl): NavigationHelper

    @Binds
    abstract fun provideMasterPasswordModel(model: MasterPasswordModelImpl): MasterPasswordModel
}