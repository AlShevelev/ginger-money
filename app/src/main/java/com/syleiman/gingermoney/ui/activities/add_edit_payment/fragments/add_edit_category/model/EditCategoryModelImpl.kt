package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.AddEditCategoryModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.AddEditCategoryModelBaseImpl
import javax.inject.Inject

class EditCategoryModelImpl
@Inject
constructor(
    private val categoryDbId: Long,
    private val db: DbStorageFacade
) : AddEditCategoryModelBaseImpl(),
    AddEditCategoryModel {

    override fun getCategory(): PaymentCategory = db.readPaymentCategory(categoryDbId)!!

    override fun updateDbCategory(category: PaymentCategory) = db.updatePaymentCategory(category)
}