package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view.ListCategoriesFragment
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view_model.ListCategoriesViewModel
import dagger.Subcomponent

@Subcomponent(modules = [ListCategoriesFragmentModuleBinds::class])
@FragmentScope
interface ListCategoriesFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ListCategoriesFragmentComponent
    }

    fun inject(fragment: ListCategoriesFragment)
    fun inject(viewModel: ListCategoriesViewModel)
}