package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.dependency_injection

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.AddCategoryModelImpl
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.AddEditCategoryModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.model.EditCategoryModelImpl
import dagger.Module
import dagger.Provides

@Module
class AddEditCategoryFragmentModule(private val categoryDbId: Long?) {
    @Provides
    internal fun provideModel(db: DbStorageFacade): AddEditCategoryModel =
        if(categoryDbId == null) {
            AddCategoryModelImpl(db)
        } else {
            EditCategoryModelImpl(categoryDbId, db)
        }
}