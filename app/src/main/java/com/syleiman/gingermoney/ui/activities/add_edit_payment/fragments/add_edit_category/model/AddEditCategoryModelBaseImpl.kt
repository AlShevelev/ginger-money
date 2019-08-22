package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model

import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsEmpty
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsTooLong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class AddEditCategoryModelBaseImpl: ModelBaseImpl(),
    AddEditCategoryModel {
    private lateinit var category: PaymentCategory

    override val maxNameLen: Int
        get() = 32

    override suspend fun load(): ModelCallResult<out PaymentCategory> =
        getValue {
            category = getCategory()
            category
        }

    override suspend fun save(name: String): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                val categoryToSave = category.copy(name = name)

                val checkResult = checkCategory(categoryToSave)
                if(checkResult != null) {
                    return@withContext checkResult
                }

                updateDbCategory(categoryToSave)
                null
            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }

    protected abstract fun updateDbCategory(category: PaymentCategory)

    protected abstract fun getCategory():  PaymentCategory

    private fun checkCategory(category: PaymentCategory): DisplayingError? =
        when {
            category.name.isBlank() -> NameIsEmpty()
            category.name.length > maxNameLen -> NameIsTooLong()
            else -> null
        }
}