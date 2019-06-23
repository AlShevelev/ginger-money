package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model.EditAccountModelImpl
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model.EditAccountModel
import dagger.Binds
import dagger.Module

@Module
abstract class EditAccountFragmentModuleBinds {
    @Binds
    abstract fun provideEditAccountModel(model: EditAccountModelImpl): EditAccountModel
}