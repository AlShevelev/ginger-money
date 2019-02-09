package com.syleiman.gingermoney.ui.activities.main.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.main.MainActivity
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.header.SettingsHeader
import dagger.Subcomponent

/**
 *
 */
@Subcomponent(modules = [MainActivityModuleBinds::class])
@ActivityScope
interface MainActivityComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): MainActivityComponent
    }

    fun inject(activity : MainActivity)

    fun inject(header : SettingsHeader)
}