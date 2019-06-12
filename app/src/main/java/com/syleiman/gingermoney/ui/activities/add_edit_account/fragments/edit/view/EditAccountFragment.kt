package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.view

import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditAccountEditBinding
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.AccountFragmentBase
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.dependency_injection.EditAccountFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model.EditAccountModelInterface
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.view_model.EditAccountViewModel
import com.syleiman.gingermoney.ui.common.navigation.NavigationArgs
import com.syleiman.gingermoney.ui.common.widgets.EditTextWithEmoji
import kotlinx.android.synthetic.main.fragment_add_edit_account_edit.nameText as nameTextWidget
import kotlinx.android.synthetic.main.fragment_add_edit_account_edit.memoText as memoTextWidget
import kotlinx.android.synthetic.main.fragment_add_edit_account_edit.root as rootWidget

/**
 * Edit accounts page
 *
 */
class EditAccountFragment : AccountFragmentBase<FragmentAddEditAccountEditBinding, EditAccountModelInterface, EditAccountViewModel>() {

    override val memoText: EditText
        get() = memoTextWidget
    override val nameText: EditTextWithEmoji
        get() = nameTextWidget
    override val root: ConstraintLayout
        get() = rootWidget

    override fun provideViewModelType(): Class<EditAccountViewModel> = EditAccountViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_add_edit_account_edit

    override fun inject() {
        val accountDbId = arguments!!.getLong(NavigationArgs.ACCOUNT_DB_ID)
        App.injections.get<EditAccountFragmentComponent>(accountDbId).inject(this)
    }

    override fun linkViewModel(binding: FragmentAddEditAccountEditBinding, viewModel: EditAccountViewModel) {
        binding.viewModel = viewModel
    }
}
