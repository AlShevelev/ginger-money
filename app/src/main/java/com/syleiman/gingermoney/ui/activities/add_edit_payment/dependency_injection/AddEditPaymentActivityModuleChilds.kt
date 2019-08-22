package com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.dependency_injection.AddEditCategoryFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection.ListCategoriesFragmentComponent
import dagger.Module

@Module(subcomponents = [
    AddPaymentFragmentComponent::class,
    ListCategoriesFragmentComponent::class,
    AddEditCategoryFragmentComponent::class
])
class AddEditPaymentActivityModuleChilds