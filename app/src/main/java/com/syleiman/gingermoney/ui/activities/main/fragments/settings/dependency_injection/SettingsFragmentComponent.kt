package com.syleiman.gingermoney.ui.activities.main.fragments.settings.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.FragmentScope
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view.SettingsFragment
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_model.SettingsViewModel
import dagger.Subcomponent

@Subcomponent(modules = [SettingsFragmentModuleBinds::class])
@FragmentScope
interface SettingsFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): SettingsFragmentComponent
    }

    fun inject(fragment : SettingsFragment)
    fun inject(viewModel: SettingsViewModel)
}