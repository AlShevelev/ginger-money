package com.syleiman.gingermoney.application.dependencyInjection

import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.application.dependencyInjection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.storages.db.typeConverters.MoneyTypeConverter
import com.syleiman.gingermoney.ui.dependencyInjection.UIComponent
import dagger.Component

@Component(modules = [
    AppModule::class,
    AppModuleBinds::class,
    AppModuleChilds::class
])
@ApplicationScope
interface AppComponent {
    val ui: UIComponent.Builder

    /**  */
    fun inject(app: App)

    /**  */
    fun inject(converter: MoneyTypeConverter)
}