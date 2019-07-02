package com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.AddEditPaymentActivity
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import dagger.Subcomponent

@Subcomponent(modules = [AddEditPaymentActivityModuleBinds::class, AddEditPaymentActivityModuleChilds::class])
@ActivityScope
interface AddEditPaymentActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddEditPaymentActivityComponent
    }

    val addPaymentFragment: AddPaymentFragmentComponent.Builder

    fun inject(activity: AddEditPaymentActivity)
}