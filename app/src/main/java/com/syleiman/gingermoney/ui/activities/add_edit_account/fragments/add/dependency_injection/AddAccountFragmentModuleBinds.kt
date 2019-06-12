package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModel
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModelInterface
import dagger.Binds
import dagger.Module

@Module
abstract class AddAccountFragmentModuleBinds {
    @Binds
    abstract fun provideAddAccountModel(model: AddAccountModel): AddAccountModelInterface
}