package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.dto.entities.sortDate
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dto.CategoryListItem
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import javax.inject.Inject

class ListCategoriesModelImpl
@Inject
constructor(
    private val db: DbStorageFacade
) : ModelBaseImpl(),
    ListCategoriesModel {

    override suspend fun getCategoriesList(): ModelCallResult<out List<CategoryListItem>> =
        getValue {
            db.readPaymentCategories()
                .sortedByDescending { it.sortDate }
                .map { CategoryListItem(it.id!!, it.name, true) }
        }
}