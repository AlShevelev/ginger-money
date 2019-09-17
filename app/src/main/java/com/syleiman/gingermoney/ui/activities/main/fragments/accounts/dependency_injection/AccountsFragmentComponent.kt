package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.AccountsFragment
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.view_holders.GroupViewHolder
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.AccountsViewModel
import dagger.Subcomponent

@Subcomponent(modules = [AccountsFragmentModuleBinds::class])
@FragmentScope
interface AccountsFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AccountsFragmentComponent
    }

    fun inject(fragment : AccountsFragment)
    fun inject(viewModel: AccountsViewModel)

    fun inject(groupViewHolder: GroupViewHolder)
}