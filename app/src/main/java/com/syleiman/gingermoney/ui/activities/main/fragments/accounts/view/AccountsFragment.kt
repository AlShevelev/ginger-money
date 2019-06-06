package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentMainAccountsBinding
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection.AccountsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.adapter.AccountsListAdapter
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.viewHolders.GroupViewHolderItemDecoration
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.AccountsViewModel
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeaderLinkInterface
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import kotlinx.android.synthetic.main.fragment_main_accounts.*
import javax.inject.Inject

/**
 * Accounts page
 */
class AccountsFragment :
    FragmentBase<FragmentMainAccountsBinding, AccountsModelInterface, AccountsViewModel>(),
    AccountsFragmentHeaderInterface {

    private lateinit var accountsListAdapter: AccountsListAdapter
    private lateinit var accountsListLayoutManager: LinearLayoutManager

    @Inject
    internal lateinit var headerLink: AccountsHeaderLinkInterface

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    override fun provideViewModelType(): Class<AccountsViewModel> = AccountsViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_main_accounts

    override fun inject() = App.injections.get<AccountsFragmentComponent>().inject(this)

    override fun linkViewModel(binding: FragmentMainAccountsBinding, viewModel: AccountsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.accountsListData.observe({this.lifecycle}) {
            updateAccountsList(it)
        }
    }

    override fun onStart() {
        super.onStart()
        headerLink.attach(this)
    }

    override fun onStop() {
        super.onStop()
        headerLink.detachFragment()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewActive()
    }

    override fun onAddButtonClick() = navigation.moveToAddEdiAccount(this)

    private fun updateAccountsList(accounts: List<ListItem>) {
        if(!::accountsListAdapter.isInitialized) {
            accountsListLayoutManager = LinearLayoutManager(context)

            accountsListAdapter = AccountsListAdapter(viewModel)
            accountsListAdapter.setHasStableIds(true)

            accountsList.addItemDecoration(GroupViewHolderItemDecoration(resourcesProvider))
            accountsList.isSaveEnabled = false
            accountsList.itemAnimator = null
            accountsList.layoutManager = accountsListLayoutManager
            accountsList.adapter = accountsListAdapter
        }

        accountsListAdapter.update(accounts)
    }
}
