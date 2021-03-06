package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ViewDataBinding
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModel
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.dto.errors.GroupIsEmptyError
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.dto.view_commands.StartSelectAccountGroupCommand
import com.syleiman.gingermoney.ui.activities.add_edit_account.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.MemoIsTooLongError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsEmptyError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsTooLongError
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.*
import com.syleiman.gingermoney.ui.common.widgets.EditTextWithEmoji
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboard
import javax.inject.Inject

abstract class AccountFragmentBase<TB: ViewDataBinding, TM: AddAccountModel, TVM: AccountViewModelBase<TM>>:
    FragmentBase<TB, TM, TVM>() {

    private lateinit var amountKeyboard: AmountKeyboard

    @Inject
    internal lateinit var navigation: NavigationHelper

    protected abstract val memoText: EditText
    protected abstract val nameText: EditTextWithEmoji
    protected abstract val root: ConstraintLayout

    override fun observeViewModel(viewModel: TVM) {
        viewModel.dialogCommands.observe({this.lifecycle}) {
            processDialogCommand(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memoText.setOnFocusChangeListener { _, hasFocus -> viewModel.onMemoTextFieldFocusChanged(hasFocus) }
        nameText.setOnFocusChangeListener { _, hasFocus -> viewModel.onNameTextFieldFocusChanged(hasFocus) }
        nameText.setOnEmojiKeyboardOpenListener { viewModel.onEmojiKeyboardOpened() }
    }

    override fun processViewCommand(command: ViewCommand) {
        when (command) {
            is ShowAmountKeyboard -> showAmountKeyboard(command)
            is HideSoftKeyboard -> hideSoftKeyboard()
            is HideEmojiKeyboard -> hideEmojiKeyboard()
            is HideAmountKeyboard -> hideAmountKeyboard()
            is ShowErrorCommand -> showError(command.error)
            is MoveBackViewCommand -> navigation.moveBack(this, true)
            else -> throw UnsupportedOperationException("This command is not supported: $command")
        }
    }

    private fun processDialogCommand(command: ViewCommand) {
        when(command) {
            is StartSelectAccountGroupCommand -> startSelectAccountGroup(command.groups)
            else -> throw UnsupportedOperationException("This command is not supported: $command")
        }
    }

    private fun startSelectAccountGroup(groups: List<AccountGroup>) {
        activeDialog = uiUtils.showOneOptionDialog(
            requireContext(),
            groups.map { resourcesProvider.getAccountGroupString(it) },
            resourcesProvider.getString(R.string.addEditAccountGroupDialogTitle)
        ) { resultIndex ->
            resultIndex?.also { viewModel.onAccountGroupSelected(it) }
        }
    }

    private fun showAmountKeyboard(command: ShowAmountKeyboard) {
        if(!::amountKeyboard.isInitialized) {
            amountKeyboard = AmountKeyboard(root, requireContext(), command.currencies, command.canEditCurrency, command.canEditSign)
            amountKeyboard.setOnEditingListener { viewModel.onAmountEdit(it) }
        }

        amountKeyboard.show(command.value)
    }

    private fun hideSoftKeyboard() {
        uiUtils.setSoftKeyboardVisibility(root, false)

        memoText.clearFocus()
        nameText.clearFocus()
    }

    private fun hideEmojiKeyboard() {
        nameText.hideEmojiKeyboard()
        nameText.clearFocus()
    }

    private fun hideAmountKeyboard() {
        if(::amountKeyboard.isInitialized) {
            amountKeyboard.hide()
        }
    }

    private fun showError(error: DisplayingError) {
        val errorResId = when(error) {
            is GroupIsEmptyError -> R.string.addEditAccountGroupIsEmptyError
            is MemoIsTooLongError -> R.string.addEditAccountMemoIsTooLongError
            is NameIsEmptyError -> R.string.addEditAccountNameIsEmptyError
            is NameIsTooLongError -> R.string.addEditAccountNameIsTooLongError
            else -> throw java.lang.UnsupportedOperationException("Command is not supported: ${error::class.simpleName}")
        }
        uiUtils.showError(resourcesProvider.getString(errorResId))
    }
}