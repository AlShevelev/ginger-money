package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view.ListCategoryFragment
import dagger.Subcomponent

@Subcomponent(modules = [ListCategoryFragmentModuleBinds::class])
@FragmentScope
interface ListCategoryFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ListCategoryFragmentComponent
    }

    fun inject(fragment: ListCategoryFragment)
}