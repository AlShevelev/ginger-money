package com.syleiman.gingermoney.ui.activities.addEditAccount.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.addEditAccount.AddEditAccountActivity
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
}