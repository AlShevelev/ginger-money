package com.syleiman.gingermoney.ui.activities.addEditAccount.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.addEditAccount.AddEditAccountActivity
import com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.view.AddAccountFragment
import com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.view_model.AddAccountViewModel
import dagger.Subcomponent

/**
 *
 */
@Subcomponent(modules = [AddEditAccountActivityModuleBinds::class])
@ActivityScope
interface AddEditAccountActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddEditAccountActivityComponent
    }

    fun inject(activity: AddEditAccountActivity)

    fun inject(activity: AddAccountFragment)
    fun inject(activity: AddAccountViewModel)
}