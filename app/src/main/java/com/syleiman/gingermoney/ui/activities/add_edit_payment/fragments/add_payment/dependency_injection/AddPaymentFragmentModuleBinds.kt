package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model.AddPaymentModel
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model.AddPaymentModelImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AddPaymentFragmentModuleBinds {
    @Binds
    abstract fun provideAddAccountModel(model: AddPaymentModelImpl): AddPaymentModel
}