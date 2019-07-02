package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditPaymentAddBinding
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection.AddPaymentFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model.AddPaymentModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view_model.AddPaymentViewModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import javax.inject.Inject


class AddPaymentFragment :
    FragmentBase<FragmentAddEditPaymentAddBinding, AddPaymentModel, AddPaymentViewModel>() {

    @Inject
    internal lateinit var navigation: NavigationHelper

    override fun provideViewModelType(): Class<AddPaymentViewModel> = AddPaymentViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_add_edit_payment_add

    override fun inject() = App.injections.get<AddPaymentFragmentComponent>().inject(this)

    override fun linkViewModel(binding: FragmentAddEditPaymentAddBinding, viewModel: AddPaymentViewModel) {
        binding.viewModel = viewModel
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        viewModel.accountsListData.observe({this.viewLifecycleOwner.lifecycle}) {
//            updateAccountsList(it)
//        }
//
//        viewModel.dialogCommands.observe({this.viewLifecycleOwner.lifecycle}) {
//            processDialogCommand(it)
//        }
//
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.onViewActive()
//    }

}
