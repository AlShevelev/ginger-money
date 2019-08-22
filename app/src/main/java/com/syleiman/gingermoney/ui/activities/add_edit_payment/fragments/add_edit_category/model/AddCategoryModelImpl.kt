package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.AddEditCategoryModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.AddEditCategoryModelBaseImpl
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class AddCategoryModelImpl
@Inject
constructor(
    private val db: DbStorageFacade
) : AddEditCategoryModelBaseImpl(),
    AddEditCategoryModel {

    override fun getCategory(): PaymentCategory =  PaymentCategory(null, "", ZonedDateTime.now(), null)

    override fun updateDbCategory(category: PaymentCategory) = db.createPaymentCategory(category)
}