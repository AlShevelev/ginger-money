package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditPaymentAddEditCategoryBinding
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.dependency_injection.AddEditCategoryFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.AddEditCategoryModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.view_model.AddEditCategoryViewModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsEmptyError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsTooLongError
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.*
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs
import kotlinx.android.synthetic.main.fragment_add_edit_payment_add_edit_category.*
import javax.inject.Inject

class AddEditCategoryFragment:
    FragmentBase<FragmentAddEditPaymentAddEditCategoryBinding, AddEditCategoryModel, AddEditCategoryViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun provideViewModelType(): Class<AddEditCategoryViewModel> = AddEditCategoryViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_add_edit_payment_add_edit_category

    override fun inject() {
        var accountDbId = arguments?.getLong(NavigationArgs.DB_ID)
        if(accountDbId == 0L) {
            accountDbId = null
        }

        App.injections.get<AddEditCategoryFragmentComponent>(accountDbId).inject(this)
    }

    override fun releaseInjection() {
        App.injections.release<AddEditCategoryFragmentComponent>()
    }

    override fun linkViewModel(binding: FragmentAddEditPaymentAddEditCategoryBinding, viewModel: AddEditCategoryViewModel) {
        binding.viewModel = viewModel
    }

    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveBackViewCommand -> navigation.moveBack(requireActivity())
            is ShowErrorCommand ->  {
                when(command.error) {
                    is NameIsEmptyError -> uiUtils.showError(R.string.addEditPaymentCategoryNameIsEmptyError)
                    is NameIsTooLongError -> uiUtils.showError(R.string.addEditPaymentCategoryNameIsTooLongError)
                }
            }
            is HideSoftKeyboard -> hideSoftKeyboard()
            is HideEmojiKeyboard -> hideEmojiKeyboard()
        }
    }

    private fun hideSoftKeyboard() {
        uiUtils.setSoftKeyboardVisibility(root, false)
        nameText.clearFocus()
    }

    private fun hideEmojiKeyboard() {
        nameText.hideEmojiKeyboard()
        nameText.clearFocus()
    }
}
