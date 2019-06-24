package com.syleiman.gingermoney.ui.activities.main.dependency_injection

import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dependency_injection.AccountsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dependency_injection.PaymentsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.settings.dependency_injection.SettingsFragmentComponent
import dagger.Module

@Module(subcomponents = [
    AccountsFragmentComponent::class,
    SettingsFragmentComponent::class,
    PaymentsFragmentComponent::class
])
class MainActivityModuleChilds