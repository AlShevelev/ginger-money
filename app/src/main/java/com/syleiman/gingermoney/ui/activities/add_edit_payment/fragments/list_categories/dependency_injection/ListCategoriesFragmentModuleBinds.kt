package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.model.ListCategoriesModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.model.ListCategoriesModelImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ListCategoriesFragmentModuleBinds {
    @Binds
    abstract fun provideListCategoryModel(model: ListCategoriesModelImpl): ListCategoriesModel
}