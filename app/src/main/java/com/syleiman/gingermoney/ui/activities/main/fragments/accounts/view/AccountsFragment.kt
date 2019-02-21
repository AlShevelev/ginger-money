package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentMainAccountsBinding
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.AccountsViewModel
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase

/**
 * Accounts page
 */
class AccountsFragment : FragmentBase<FragmentMainAccountsBinding, AccountsModelInterface, AccountsViewModel>() {
    /**
     *
     */
    override fun provideViewModelType(): Class<AccountsViewModel> = AccountsViewModel::class.java

    /**
     *
     */
    override fun provideLayout(): Int = R.layout.fragment_main_accounts

    /**
     *
     */
    override fun inject() = App.injections.get<MainActivityComponent>().inject(this)

    /**
     *
     */
    override fun linkViewModel(binding: FragmentMainAccountsBinding, viewModel: AccountsViewModel) {
        binding.viewModel = viewModel
    }
}
