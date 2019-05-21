package com.syleiman.gingermoney.ui.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.UIScope
import com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.login.dependency_injection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.root.dependency_injection.RootActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboard
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

    fun inject(amountKeyboard: AmountKeyboard)
}