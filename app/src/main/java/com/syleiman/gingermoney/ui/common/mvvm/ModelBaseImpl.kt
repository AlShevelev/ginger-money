package com.syleiman.gingermoney.ui.common.mvvm

import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.GeneralError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class ModelBaseImpl: ModelBase {

    protected suspend fun saveValue(saveAction: () -> Unit): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                saveAction()
                null
            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }

    protected suspend fun <T>getValue(getAction: () -> T?): ModelCallResult<out T> =
        withContext(Dispatchers.IO) {
            try {
                ModelCallResult(null, getAction())
            } catch (ex: Exception) {
                ex.printStackTrace()
                ModelCallResult(GeneralError(), null)
            }
        }
}