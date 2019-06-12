package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view.AddAccountFragment
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_model.AddAccountViewModel
import dagger.Subcomponent

@Subcomponent(modules = [AddAccountFragmentModuleBinds::class])
@FragmentScope
interface AddAccountFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddAccountFragmentComponent
    }

    fun inject(activity: AddAccountFragment)
    fun inject(activity: AddAccountViewModel)
}