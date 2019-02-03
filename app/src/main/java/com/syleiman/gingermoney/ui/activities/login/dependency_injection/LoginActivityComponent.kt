package com.syleiman.gingermoney.ui.activities.login.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.login.LoginActivity
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.view.FingerprintFragment
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.view_model.FingerprintViewModel
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.view.MasterPasswordFragment
import com.syleiman.gingermoney.ui.activities.login.fragments.master_password.view_model.MasterPasswordViewModel
import dagger.Subcomponent

/**
 *
 */
@Subcomponent(modules = [LoginActivityModuleBinds::class, LoginActivityModule::class])
@ActivityScope
interface LoginActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): LoginActivityComponent
    }

    fun inject(activity: LoginActivity)

    fun inject(fragment : MasterPasswordFragment)

    fun inject(fragment : FingerprintFragment)

    fun inject(viewModel: MasterPasswordViewModel)

    fun inject(viewModel: FingerprintViewModel)
}