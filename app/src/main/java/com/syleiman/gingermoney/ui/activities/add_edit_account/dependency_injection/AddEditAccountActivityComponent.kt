package com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.add_edit_account.AddEditAccountActivity
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dependency_injection.AddAccountFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view.AddAccountFragment
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_model.AddAccountViewModel
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.dependency_injection.EditAccountFragmentComponent
import dagger.Subcomponent

@Subcomponent(modules = [AddEditAccountActivityModuleBinds::class, AddEditAccountActivityModuleChilds::class])
@ActivityScope
interface AddEditAccountActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddEditAccountActivityComponent
    }

    val addAccountsFragment: AddAccountFragmentComponent.Builder
    val editAccountsFragment: EditAccountFragmentComponent.Builder

    fun inject(activity: AddEditAccountActivity)
}