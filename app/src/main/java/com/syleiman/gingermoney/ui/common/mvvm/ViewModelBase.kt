package com.syleiman.gingermoney.ui.common.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import javax.inject.Inject

/**
 *
 */
@Suppress("LeakingThis")
abstract class ViewModelBase<TModel : ModelBaseInterface> : ViewModel() {

    @Inject
    internal lateinit var model: TModel

    /**
     * Direct command for view
     */
    val command: MutableLiveData<ViewCommand> = MutableLiveData()

    /**
     *
     */
    override fun onCleared() {
        super.onCleared()
        model.cancelBackgroundOperations()
    }
}