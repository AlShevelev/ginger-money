package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.databinding.FragmentMainAccountsBinding
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection.AccountsFragmentComponent
import com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor.TextColors
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.adapter.AccountsListAdapter
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.viewHolders.GroupViewHolderItemDecoration
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_commands.MoveToEditAccountCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_commands.StartSelectColorsDialogCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_commands.StartSelectCurrencyDialogCommand
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.AccountsViewModel
import com.syleiman.gingermoney.ui.common.widgets.dialogs.SelectCurrencyDialog
import com.syleiman.gingermoney.ui.activities.main.headers.accounts.AccountsHeaderLinkInterface
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor.SelectColorDialog
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.accountsListData.observe({this.viewLifecycleOwner.lifecycle}) {
            updateAccountsList(it)
        }

        viewModel.dialogCommands.observe({this.viewLifecycleOwner.lifecycle}) {
            processDialogCommand(it)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
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

    override fun onAddButtonClick() = navigation.moveToAddAccount(this)

    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveToEditAccountCommand -> navigation.moveToEditAccount(this, command.accountDbId)
        }
    }

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

    private fun processDialogCommand(command: ViewCommand) {
        when(command) {
            is StartSelectCurrencyDialogCommand -> startSelectCurrency(command.selectedCurrency, command.group)
            is StartSelectColorsDialogCommand -> startSelectColors(command.colors, command.group)
            else -> throw UnsupportedOperationException("This command is not supported: $command")
        }
    }

    private fun startSelectCurrency(selectedCurrency: Currency, group: AccountGroup?) {
        activeDialog = SelectCurrencyDialog(
            requireContext(),
            selectedCurrency,
            resourcesProvider.getString(R.string.mainAccountsSelectCurrency),
            resourcesProvider.getString(R.string.commonOk),
            resourcesProvider.getString(R.string.commonCancel)
        ) { resultCurrency ->
            resultCurrency?.also { viewModel.onOnCurrencySelected(resultCurrency, group) }
        }
        .show()
    }

    private fun startSelectColors(colors: TextColors, group: AccountGroup) {
        activeDialog = SelectColorDialog(
            requireContext(),
            colors.foregroundColor,
            colors.backgroundColor,
            MoneyHardCentsFormatter().format(Currency.USD.toMoney(1000.0)),
            resourcesProvider.getString(R.string.dialogSelectColorsTitle),
            resourcesProvider.getString(R.string.commonOk),
            resourcesProvider.getString(R.string.commonCancel)
        ) { selectedColors ->
            selectedColors?.let { viewModel.onColorsSelected(it, group) }
        }
        .show()
    }
}
