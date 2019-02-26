package com.syleiman.gingermoney.ui.activities.addEditAccount.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.model.AddAccountModel
import com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.add.model.AddAccountModelInterface
import com.syleiman.gingermoney.ui.activities.addEditAccount.navigation.NavigationHelper
import com.syleiman.gingermoney.ui.activities.addEditAccount.navigation.NavigationHelperInterface
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model.AccountsModelInterface
import dagger.Binds
import dagger.Module

@Module
abstract class AddEditAccountActivityModuleBinds {
    /**
     *
     */
    @Binds
    @ActivityScope
    abstract fun provideNavigationHelper(helper: NavigationHelper): NavigationHelperInterface

    /**
     *
     */
    @Binds
    abstract fun provideAddAccountModel(model: AddAccountModel): AddAccountModelInterface
}