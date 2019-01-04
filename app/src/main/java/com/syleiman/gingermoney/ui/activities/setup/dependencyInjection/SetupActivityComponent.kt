package com.syleiman.gingermoney.ui.activities.setup.dependencyInjection

import com.syleiman.gingermoney.application.dependencyInjection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.setup.fragments.BaseCurrencyFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.MasterPasswordFragment
import com.syleiman.gingermoney.ui.activities.setup.fragments.ProtectionMethodFragment
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
}