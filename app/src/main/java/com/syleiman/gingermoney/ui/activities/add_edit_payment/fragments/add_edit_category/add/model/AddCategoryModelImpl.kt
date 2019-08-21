package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsEmpty
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.NameIsTooLong
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddCategoryModelImpl
@Inject
constructor(
    private val db: DbStorageFacade
): ModelBaseImpl(), AddCategoryModel {

    override val maxNameLen: Int
        get() = 32

    override suspend fun save(category: PaymentCategory): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                val checkResult = checkCategory(category)
                if(checkResult != null) {
                    return@withContext checkResult
                }

                db.createPaymentCategory(category)      // todo to overrided method
                null
            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }

    private fun checkCategory(category: PaymentCategory): DisplayingError? =
        when {
            category.name.isBlank() -> NameIsEmpty()
            category.name.length > maxNameLen -> NameIsTooLong()
            else -> null
        }
}