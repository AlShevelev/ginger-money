package com.syleiman.gingermoney.ui.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.UIScope
import com.syleiman.gingermoney.ui.activities.addEditAccount.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.login.dependency_injection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.root.dependency_injection.RootActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import dagger.Subcomponent

/**
 *
 */
@Subcomponent(modules = [
    UIModuleBinds::class,
    UIModuleChilds::class
])
@UIScope
interface UIComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): UIComponent
    }

    val rootActivity: RootActivityComponent.Builder

    val setupActivity: SetupActivityComponent.Builder

    val loginActivity: LoginActivityComponent.Builder

    val mainActivity: MainActivityComponent.Builder

    val addEditAccountActivity: AddEditAccountActivityComponent.Builder
}