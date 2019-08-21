package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.model.AddCategoryModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.add.model.AddCategoryModelImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AddCategoryFragmentModuleBinds {
    @Binds
    abstract fun provideListCategoryModel(model: AddCategoryModelImpl): AddCategoryModel
}