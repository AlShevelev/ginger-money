package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.view.AddCategoryFragment
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.viewModel.AddCategoryViewModel
import dagger.Subcomponent

@Subcomponent(modules = [AddCategoryFragmentModuleBinds::class])
@FragmentScope
interface AddCategoryFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddCategoryFragmentComponent
    }

    fun inject(fragment: AddCategoryFragment)
    fun inject(viewModel: AddCategoryViewModel)
}