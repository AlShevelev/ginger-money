package com.syleiman.gingermoney.ui.dependency_injection

import com.syleiman.gingermoney.ui.activities.add_edit_account.dependency_injection.AddEditAccountActivityComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection.AddEditPaymentActivityComponent
import com.syleiman.gingermoney.ui.activities.login.dependency_injection.LoginActivityComponent
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dependency_injection.PaymentsFragmentComponent
import com.syleiman.gingermoney.ui.activities.root.dependency_injection.RootActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import dagger.Module

@Module(subcomponents = [
    RootActivityComponent::class,
    SetupActivityComponent::class,
    LoginActivityComponent::class,
    MainActivityComponent::class,
    AddEditAccountActivityComponent::class,
    AddEditPaymentActivityComponent::class
])
class UIModuleChilds