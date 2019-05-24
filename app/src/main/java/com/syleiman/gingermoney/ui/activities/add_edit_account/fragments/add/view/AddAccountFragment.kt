package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view

import android.os.Bundle
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditAccountAddBinding
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_commands.ShowAmountKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_commands.StartSelectAccountGroupCommand
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_model.AddAccountViewModel
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboard
import kotlinx.android.synthetic.main.fragment_add_edit_account_add.*

/**
 * Add accounts page
 */
class AddAccountFragment : FragmentBase<FragmentAddEditAccountAddBinding, AddAccountModelInterface, AddAccountViewModel>() {

    private lateinit var amountKeyboard: AmountKeyboard

    /**
     *
     */
    override fun provideViewModelType(): Class<AddAccountViewModel> = AddAccountViewModel::class.java

    /**
     *
     */
    override fun provideLayout(): Int = R.layout.fragment_add_edit_account_add

    /**
     *
     */
    override fun inject() = App.injections.get<AddEditAccountActivityComponent>().inject(this)

    /**
     *
     */
    override fun linkViewModel(binding: FragmentAddEditAccountAddBinding, viewModel: AddAccountViewModel) {
        binding.viewModel = viewModel
    }

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.dialogCommands.observe({this.lifecycle}) {
            processDialogCommand(it)
        }
    }

    /**
     *
     */
    override fun processViewCommand(command: ViewCommand) {
        when (command) {
            is ShowAmountKeyboard -> showAmountKeyboard(command)
        }
    }

    /**
     *
     */
    private fun processDialogCommand(command: ViewCommand) {
        when(command) {
            is StartSelectAccountGroupCommand -> startSelectAccountGroup(command.groups)
            else -> throw UnsupportedOperationException("This command is not supported: $command")
        }
    }

    /**
     *
     */
    private fun startSelectAccountGroup(groups: List<AccountGroup>) {
        activeDialog = uiUtils.showOneOptionDialog(
            requireContext(),
            groups.map { resourcesProvider.getAccountGroupString(it) },
            resourcesProvider.getString(R.string.addEditAccountGroupDialogTitle)
        ) { resultIndex ->
            resultIndex?.also { viewModel.onAccountGroupSelected(it) }
        }
    }

    /**
     *
     */
    private fun showAmountKeyboard(command: ShowAmountKeyboard) {
        if(!::amountKeyboard.isInitialized) {
            amountKeyboard = AmountKeyboard(root, requireContext(), command.currencies)
            amountKeyboard.setOnEditingListener { viewModel.onAmountEdit(it) }
        }
        amountKeyboard.show(command.value)
    }
}
