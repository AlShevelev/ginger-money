package com.syleiman.gingermoney.ui.activities.main.fragments.payments.dependency_injection

import com.syleiman.gingermoney.ui.activities.main.fragments.payments.model.PaymentsModel
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.model.PaymentsModelImpl
import dagger.Binds
import dagger.Module

@Module
abstract class PaymentsFragmentModuleBinds {
    @Binds
    abstract fun providePaymentsModel(model: PaymentsModelImpl): PaymentsModel
}