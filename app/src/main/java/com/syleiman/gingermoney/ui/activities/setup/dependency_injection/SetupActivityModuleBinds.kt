package com.syleiman.gingermoney.ui.activities.setup.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model.BaseCurrencyModelImpl
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model.BaseCurrencyModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model.MasterPasswordModelImpl
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model.MasterPasswordModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model.ProtectionMethodModelImpl
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model.ProtectionMethodModel
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperImpl
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelper
import dagger.Binds
import dagger.Module

@Module
abstract class SetupActivityModuleBinds {

    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelperImpl): NavigationHelper

    @Binds
    abstract fun provideMasterPasswordModel(model: MasterPasswordModelImpl): MasterPasswordModel

    @Binds
    abstract fun provideBaseCurrencyModel(model: BaseCurrencyModelImpl): BaseCurrencyModel

    @Binds
    abstract fun provideProtectionMethodModel(model: ProtectionMethodModelImpl): ProtectionMethodModel
}