package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection

import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import dagger.Binds
import dagger.Module

@Module
abstract class AccountsFragmentModuleBinds {
    @Binds
    abstract fun provideAccountsModel(model: AccountsModel): AccountsModelInterface
}