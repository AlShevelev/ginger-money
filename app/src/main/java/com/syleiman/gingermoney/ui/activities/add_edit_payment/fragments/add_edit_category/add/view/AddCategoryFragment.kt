package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditPaymentAddEditCategoryBinding
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.dependency_injection.AddCategoryFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.model.AddCategoryModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.viewModel.AddCategoryViewModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsEmpty
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsTooLong
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.*
import kotlinx.android.synthetic.main.fragment_add_edit_payment_add_edit_category.*
import javax.inject.Inject

class AddCategoryFragment :
    FragmentBase<FragmentAddEditPaymentAddEditCategoryBinding, AddCategoryModel, AddCategoryViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun provideViewModelType(): Class<AddCategoryViewModel> = AddCategoryViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_add_edit_payment_add_edit_category

    override fun inject() = App.injections.get<AddCategoryFragmentComponent>().inject(this)

    override fun releaseInjection() {
        App.injections.release<AddCategoryFragmentComponent>()
    }

    override fun linkViewModel(binding: FragmentAddEditPaymentAddEditCategoryBinding, viewModel: AddCategoryViewModel) {
        binding.viewModel = viewModel
    }

    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveBackViewCommand -> navigation.moveBack(requireActivity())
            is ShowErrorCommand ->  {
                when(command.error) {
                    is NameIsEmpty -> uiUtils.showError(R.string.addEditPaymentCategoryNameIsEmptyError)
                    is NameIsTooLong -> uiUtils.showError(R.string.addEditPaymentCategoryNameIsTooLongError)
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
