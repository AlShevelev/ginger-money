package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model.EditAccountModel
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model.EditAccountModelInterface
import dagger.Binds
import dagger.Module

@Module
abstract class EditAccountFragmentModuleBinds {
    @Binds
    abstract fun provideEditAccountModel(model: EditAccountModel): EditAccountModelInterface
}