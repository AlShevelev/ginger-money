package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view.AddPaymentFragment
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_model.AddPaymentViewModel
import dagger.Subcomponent

@Subcomponent(modules = [AddPaymentFragmentModuleBinds::class])
@FragmentScope
interface AddPaymentFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddPaymentFragmentComponent
    }

    fun inject(activity: AddPaymentFragment)
    fun inject(activity: AddPaymentViewModel)
}