package com.syleiman.gingermoney.ui.common.mvvm

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 *
 */
@Suppress("LeakingThis")
abstract class ViewModelBase<TModel : ModelBaseInterface> : ViewModel() {

    @Inject
    internal lateinit var model: TModel

    /**
     *
     */
    override fun onCleared() {
        super.onCleared()
        model.cancelBackgroundOperations()
    }
}