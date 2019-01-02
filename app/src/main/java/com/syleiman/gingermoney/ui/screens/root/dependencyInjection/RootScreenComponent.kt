package com.syleiman.gingermoney.ui.screens.root.dependencyInjection

import com.syleiman.gingermoney.application.dependencyInjection.scopes.ScreenScope
import com.syleiman.gingermoney.ui.screens.root.RootScreenActivity
import dagger.Subcomponent

/**
 *
 */
@Subcomponent()
@ScreenScope
interface RootScreenComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): RootScreenComponent
    }

    /** */
    fun inject(rootScreenActivity : RootScreenActivity)
}