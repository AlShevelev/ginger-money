package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view

import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditAccountAddBinding
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dependency_injection.AddAccountFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModel
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_model.AddAccountViewModel
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.AccountFragmentBase
import com.syleiman.gingermoney.ui.common.widgets.EditTextWithEmoji
import kotlinx.android.synthetic.main.fragment_add_edit_account_add.nameText as nameTextWidget
import kotlinx.android.synthetic.main.fragment_add_edit_account_add.memoText as memoTextWidget
import kotlinx.android.synthetic.main.fragment_add_edit_account_add.root as rootWidget

/**
 * Add accounts page
 */
class AddAccountFragment : AccountFragmentBase<FragmentAddEditAccountAddBinding, AddAccountModel, AddAccountViewModel>() {

    override val memoText: EditText
        get() = memoTextWidget
    override val nameText: EditTextWithEmoji
        get() = nameTextWidget
    override val root: ConstraintLayout
        get() = rootWidget

    override fun provideViewModelType(): Class<AddAccountViewModel> = AddAccountViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_add_edit_account_add

    override fun inject() = App.injections.get<AddAccountFragmentComponent>().inject(this)

    override fun linkViewModel(binding: FragmentAddEditAccountAddBinding, viewModel: AddAccountViewModel) {
        binding.viewModel = viewModel
    }
}
