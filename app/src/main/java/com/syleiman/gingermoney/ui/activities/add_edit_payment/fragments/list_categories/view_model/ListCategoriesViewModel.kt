package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.view_commands.MoveToAddCategoryCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.view_commands.MoveToEditCategoryCommand
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection.ListCategoriesFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dto.CategoryListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.model.ListCategoriesModel
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import kotlinx.coroutines.launch

class ListCategoriesViewModel: ViewModelBase<ListCategoriesModel>(), ListItemEventsProcessor {
    val stubVisibility: MutableLiveData<Int> = MutableLiveData(View.VISIBLE)

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData(View.INVISIBLE)

    val categoriesList: MutableLiveData<List<CategoryListItem>> = MutableLiveData()
    val categoriesListVisibility: MutableLiveData<Int> = MutableLiveData(View.INVISIBLE)

    init {
        App.injections.get<ListCategoriesFragmentComponent>().inject(this)
    }

    fun onViewActive() {
        fillCategoriesList()
    }

    fun onAddCategoryClick() {
        command.value = MoveToAddCategoryCommand()
    }

    override fun onCategoryClick(categoryDbId: Long) {
        command.value = MoveToEditCategoryCommand(categoryDbId)
    }

    override fun onDeleteCategoryClick(categoryDbId: Long) {
        // Show confirmation Snackbar
    }

    private fun fillCategoriesList() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val categories = model.getCategoriesList()

            loadingVisibility.value = View.GONE

            processCallResult(categories) {
                categoriesList.value = it

                stubVisibility.value = if(it.isEmpty()) View.VISIBLE else View.INVISIBLE
                categoriesListVisibility.value = if(it.isEmpty()) View.INVISIBLE else View.VISIBLE
            }
        }
    }
}