package com.syleiman.gingermoney.ui.dependencyInjection

import com.syleiman.gingermoney.application.dependencyInjection.scopes.UIScope
import com.syleiman.gingermoney.ui.activities.root.dependencyInjection.RootActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
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
}