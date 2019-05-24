package com.syleiman.gingermoney.ui.activities.setup.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model.BaseCurrencyModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model.BaseCurrencyModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model.MasterPasswordModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model.MasterPasswordModelInterface
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model.ProtectionMethodModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model.ProtectionMethodModelInterface
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import dagger.Binds
import dagger.Module

@Module
abstract class SetupActivityModuleBinds {

    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelper): NavigationHelperInterface

    @Binds
    abstract fun provideMasterPasswordModel(model: MasterPasswordModel): MasterPasswordModelInterface

    @Binds
    abstract fun provideBaseCurrencyModel(model: BaseCurrencyModel): BaseCurrencyModelInterface

    @Binds
    abstract fun provideProtectionMethodModel(model: ProtectionMethodModel): ProtectionMethodModelInterface
}