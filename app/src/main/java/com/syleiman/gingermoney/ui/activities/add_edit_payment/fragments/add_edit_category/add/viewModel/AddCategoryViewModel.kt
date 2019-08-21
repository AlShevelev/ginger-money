package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.dependency_injection.AddCategoryFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.model.AddCategoryModel
import com.syleiman.gingermoney.ui.common.mvvm.ViewModelBase
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.HideEmojiKeyboard
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.HideSoftKeyboard
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.MoveBackViewCommand
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowErrorCommand
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class AddCategoryViewModel: ViewModelBase<AddCategoryModel>() {
    val name: MutableLiveData<String> = MutableLiveData()
    val nameMaxLen: MutableLiveData<Int> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)

    init {
        App.injections.get<AddCategoryFragmentComponent>().inject(this)

        nameMaxLen.value = model.maxNameLen

        initCategory()
    }

    fun onSaveButtonClick() {
        loadingVisibility.value = View.VISIBLE

        launch {
            val category = PaymentCategory(null, name.value!!, ZonedDateTime.now(), null)
            val saveResult = model.save(category)

            loadingVisibility.value = View.GONE

            if(saveResult != null) {
                command.value = ShowErrorCommand(saveResult)
            } else {
                command.value = HideSoftKeyboard()              // Close
                command.value = HideEmojiKeyboard()
                command.value = MoveBackViewCommand()
            }
        }
    }

    private fun initCategory() {
        name.value = ""
    }
}