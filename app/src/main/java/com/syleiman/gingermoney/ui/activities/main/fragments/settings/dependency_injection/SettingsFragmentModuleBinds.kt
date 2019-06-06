package com.syleiman.gingermoney.ui.activities.main.fragments.settings.dependency_injection

import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModelInterface
import dagger.Binds
import dagger.Module

@Module
abstract class SettingsFragmentModuleBinds {
    @Binds
    abstract fun provideSettingsModel(model: SettingsModel): SettingsModelInterface
}