package com.syleiman.gingermoney.ui.activities.setup.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.view.BaseCurrencyFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.view_model.BaseCurrencyViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.view.MasterPasswordFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.view_model.MasterPasswordViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.view.ProtectionMethodFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.view_model.ProtectionMethodViewModel
import dagger.Subcomponent

/**
 *
 */
@Subcomponent(modules = [SetupActivityModuleBinds::class])
@ActivityScope
interface SetupActivityComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): SetupActivityComponent
    }

    fun inject(fragment : MasterPasswordFragment)

    fun inject(fragment : BaseCurrencyFragment)

    fun inject(fragment : ProtectionMethodFragment)

    fun inject(viewModel: MasterPasswordViewModel)

    fun inject(viewModel: BaseCurrencyViewModel)

    fun inject(viewModel: ProtectionMethodViewModel)
}