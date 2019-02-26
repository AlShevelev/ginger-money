package com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditAccountAddBinding
import com.syleiman.gingermoney.ui.activities.addEditAccount.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.view_model.AddAccountViewModel
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase

/**
 * Add accounts page
 */
class AddAccountFragment : FragmentBase<FragmentAddEditAccountAddBinding, AddAccountModelInterface, AddAccountViewModel>() {

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
}
