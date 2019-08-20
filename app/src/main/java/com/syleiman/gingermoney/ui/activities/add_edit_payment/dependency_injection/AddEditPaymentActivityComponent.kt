package com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.add_edit_payment.AddEditPaymentActivity
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.account.AccountsKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.category.CategoriesKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection.ListCategoriesFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.AddPaymentHeader
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.list_categories.ListCategoriesHeader
import dagger.Subcomponent

@Subcomponent(modules = [AddEditPaymentActivityModuleBinds::class, AddEditPaymentActivityModuleChilds::class])
@ActivityScope
interface AddEditPaymentActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddEditPaymentActivityComponent
    }

    val addPaymentFragment: AddPaymentFragmentComponent.Builder
    val listCategoriesFragment: ListCategoriesFragmentComponent.Builder

    fun inject(activity: AddEditPaymentActivity)

    fun inject(keyboard: AccountsKeyboard)
    fun inject(keyboard: CategoriesKeyboard)

    fun inject(header: AddPaymentHeader)
    fun inject(header: ListCategoriesHeader)
}