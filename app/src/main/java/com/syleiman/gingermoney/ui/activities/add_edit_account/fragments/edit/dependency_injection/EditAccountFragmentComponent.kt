package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.view.EditAccountFragment
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.view_model.EditAccountViewModel
import dagger.Subcomponent

@Subcomponent(modules = [EditAccountFragmentModuleBinds::class, EditAccountFragmentModule::class])
@FragmentScope
interface EditAccountFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun init(module: EditAccountFragmentModule): Builder
        fun build(): EditAccountFragmentComponent
    }

    fun inject(activity: EditAccountFragment)
    fun inject(activity: EditAccountViewModel)
}