package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.dependency_injection.AddEditCategoryFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.AddEditCategoryModel
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.HideEmojiKeyboard
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.HideSoftKeyboard
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.MoveBackViewCommand
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch

class AddEditCategoryViewModel: ViewModelBase<AddEditCategoryModel>() {
    val name: MutableLiveData<String> = MutableLiveData()
    val nameMaxLen: MutableLiveData<Int> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)

    init {
        App.injections.get<AddEditCategoryFragmentComponent>().inject(this)

        nameMaxLen.value = model.maxNameLen

        initCategory()
    }

    fun onSaveButtonClick() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val saveResult = model.save(name.value!!)

            loadingVisibility.value = View.GONE

            if(saveResult != null) {
                command.value = ShowErrorCommand(saveResult)
            } else {
                close()
            }
        }
    }

    private fun initCategory() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val category = model.load()

            loadingVisibility.value = View.GONE

            category.processCallResult({
                name.value = it.name
            }, {
                close()
            })
        }
    }

    private fun close() {
        command.value = HideSoftKeyboard()
        command.value = HideEmojiKeyboard()
        command.value = MoveBackViewCommand()
    }
}