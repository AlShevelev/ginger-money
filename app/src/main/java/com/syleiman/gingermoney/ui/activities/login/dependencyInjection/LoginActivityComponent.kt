package com.syleiman.gingermoney.ui.activities.login.dependencyInjection

import com.syleiman.gingermoney.application.dependencyInjection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.login.LoginActivity
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.view.FingerprintFragment
import com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.view.MasterPasswordFragment
import dagger.Subcomponent

/**
 *
 */
@Subcomponent(modules = [LoginActivityModuleBinds::class])
@ActivityScope
interface LoginActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): LoginActivityComponent
    }

    fun inject(activity: LoginActivity)

    fun inject(fragment : MasterPasswordFragment)

    fun inject(fragment : FingerprintFragment)

//    fun inject(viewModel: MasterPasswordViewModel)
//
//    fun inject(viewModel: FingerprintViewModel)
}