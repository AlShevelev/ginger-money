package com.syleiman.gingermoney.ui.activities.main.fragments.payments.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.PaymentsFragment
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view_model.PaymentsViewModel
import dagger.Subcomponent

@Subcomponent(modules = [PaymentsFragmentModuleBinds::class])
@FragmentScope
interface PaymentsFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): PaymentsFragmentComponent
    }

    fun inject(fragment : PaymentsFragment)
    fun inject(viewModel: PaymentsViewModel)
}