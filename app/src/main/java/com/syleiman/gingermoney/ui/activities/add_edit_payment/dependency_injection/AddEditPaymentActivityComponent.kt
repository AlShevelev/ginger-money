package com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.AddEditPaymentActivity
import dagger.Subcomponent

@Subcomponent(modules = [AddEditPaymentActivityModuleBinds::class])
@ActivityScope
interface AddEditPaymentActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddEditPaymentActivityComponent
    }

    fun inject(activity: AddEditPaymentActivity)
}