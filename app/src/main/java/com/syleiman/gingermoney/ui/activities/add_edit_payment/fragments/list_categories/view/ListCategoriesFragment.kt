package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.databinding.FragmentAddEditPaymentListCategoriesBinding
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.view_commands.MoveToAddCategoryCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.view_commands.MoveToEditCategoryCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection.ListCategoriesFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dto.CategoryListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.model.ListCategoriesModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view.list.ListCategoriesAdapter
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view_model.ListCategoriesViewModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.list_categories.ListCategoriesHeaderLink
import com.syleiman.gingermoney.ui.activities.add_edit_payment.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.common.mvvm.FragmentBase
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ViewCommand
import kotlinx.android.synthetic.main.fragment_add_edit_payment_list_categories.*
import javax.inject.Inject

class ListCategoriesFragment :
    FragmentBase<FragmentAddEditPaymentListCategoriesBinding, ListCategoriesModel, ListCategoriesViewModel>(),
    ListCategoryFragmentHeaderLink {

    private lateinit var categoriesListAdapter: ListCategoriesAdapter
    private lateinit var categoriesListLayoutManager: LinearLayoutManager
    private var isListInitialized = false

    @Inject
    internal lateinit var navigation: NavigationHelper

    @Inject
    lateinit var linkToHeader: ListCategoriesHeaderLink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linkToHeader.attachFragment(this)
    }

    override fun provideViewModelType(): Class<ListCategoriesViewModel> = ListCategoriesViewModel::class.java

    override fun provideLayout(): Int = R.layout.fragment_add_edit_payment_list_categories

    override fun inject() = App.injections.get<ListCategoriesFragmentComponent>().inject(this)

    override fun releaseInjection() {
        App.injections.release<ListCategoriesFragmentComponent>()
    }

    override fun linkViewModel(binding: FragmentAddEditPaymentListCategoriesBinding, viewModel: ListCategoriesViewModel) {
        binding.viewModel = viewModel
    }

    override fun observeViewModel(viewModel: ListCategoriesViewModel) {
        isListInitialized = false

        viewModel.categoriesList.observe({this.viewLifecycleOwner.lifecycle}) {
            updateCategoriesList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewActive()
    }

    override fun onAddButtonClick() = viewModel.onAddCategoryClick()

    override fun processViewCommand(command: ViewCommand) {
        when(command) {
            is MoveToAddCategoryCommand -> navigation.moveToAddCategory(this)
            is MoveToEditCategoryCommand -> navigation.moveToEditCategory(this, command.categoryDbId)
        }
    }

    private fun updateCategoriesList(categories: List<CategoryListItem>) {
        if(!isListInitialized) {
            categoriesListLayoutManager = LinearLayoutManager(context)

            categoriesListAdapter = ListCategoriesAdapter(viewModel)
            categoriesListAdapter.setHasStableIds(true)

            categoriesList.isSaveEnabled = false
            categoriesList.itemAnimator = null
            categoriesList.layoutManager = categoriesListLayoutManager
            categoriesList.adapter = categoriesListAdapter

            isListInitialized = true
        }

        categoriesListAdapter.update(categories)
        categoriesList.scrollToPosition(0)
    }
}

