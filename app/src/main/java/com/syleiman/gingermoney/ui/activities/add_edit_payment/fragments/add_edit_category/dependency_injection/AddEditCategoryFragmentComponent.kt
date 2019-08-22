package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.view.AddEditCategoryFragment
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.view_model.AddEditCategoryViewModel
import dagger.Subcomponent

@Subcomponent(modules = [AddEditCategoryFragmentModule::class])
@FragmentScope
interface AddEditCategoryFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun init(module: AddEditCategoryFragmentModule): Builder
        fun build(): AddEditCategoryFragmentComponent
    }

    fun inject(fragment: AddEditCategoryFragment)
    fun inject(fragment: AddEditCategoryViewModel)
}