package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.model

import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase

interface AddCategoryModel: ModelBase {
    val maxNameLen: Int

    suspend fun save(category: PaymentCategory): DisplayingError?
}