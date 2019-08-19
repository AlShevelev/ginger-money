package com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection.ListCategoryFragmentComponent
import dagger.Module

@Module(subcomponents = [
    AddPaymentFragmentComponent::class,
    ListCategoryFragmentComponent::class])
class AddEditPaymentActivityModuleChilds