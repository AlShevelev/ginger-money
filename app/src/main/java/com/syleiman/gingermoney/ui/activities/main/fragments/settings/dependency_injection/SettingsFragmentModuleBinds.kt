package com.syleiman.gingermoney.ui.activities.main.fragments.settings.dependency_injection

import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModelImpl
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.model.SettingsModel
import dagger.Binds
import dagger.Module

@Module
abstract class SettingsFragmentModuleBinds {
    @Binds
    abstract fun provideSettingsModel(model: SettingsModelImpl): SettingsModel
}