package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.model

import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dto.CategoryListItem
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

interface ListCategoriesModel: ModelBase {
    suspend fun getCategoriesList(): ModelCallResult<out List<CategoryListItem>>
}