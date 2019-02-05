package com.syleiman.gingermoney.application.dependency_injection

import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.application.dependency_injection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.storages.db.type_converters.MoneyTypeConverter
import com.syleiman.gingermoney.core.works.update_currency_rate.UpdateCurrencyRatesWorker
import com.syleiman.gingermoney.ui.dependency_injection.UIComponent
import dagger.Component

@Component(modules = [
    AppModule::class,
    AppModuleBinds::class,
    AppModuleChilds::class
])
@ApplicationScope
interface AppComponent {
    val ui: UIComponent.Builder

    /**
     *
     */
    fun inject(app: App)

    /**
     *
     */
    fun inject(converter: MoneyTypeConverter)

    /**
     *
     */
    fun inject(worker: UpdateCurrencyRatesWorker)
}