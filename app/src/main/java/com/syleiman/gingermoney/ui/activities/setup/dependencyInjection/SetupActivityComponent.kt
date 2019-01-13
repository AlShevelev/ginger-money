package com.syleiman.gingermoney.ui.activities.setup.dependencyInjection

import com.syleiman.gingermoney.application.dependencyInjection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.view.BaseCurrencyFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.viewModel.BaseCurrencyViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.view.MasterPasswordFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.viewModel.MasterPasswordViewModel
import com.syleiman.gingermoney.ui.activities.setup.fragments.protectionMethod.view.ProtectionMethodFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.protectionMethod.viewModel.ProtectionMethodViewModel
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