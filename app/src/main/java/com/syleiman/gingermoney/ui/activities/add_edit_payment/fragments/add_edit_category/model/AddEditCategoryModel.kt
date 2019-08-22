package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model

import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError

interface AddEditCategoryModel: ModelBase {
    val maxNameLen: Int

    suspend fun load(): ModelCallResult<out PaymentCategory>

    suspend fun save(name: String): DisplayingError?
}