package com.syleiman.gingermoney.ui.common.mvvm

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 *
 */
abstract class ViewModelBase<TM : ModelBaseInterface> : ViewModel() {

    @Inject
    internal lateinit var model: TM

    /**
     *
     */
    override fun onCleared() {
        super.onCleared()
        model.cancelBackgroundOperations()
    }
}