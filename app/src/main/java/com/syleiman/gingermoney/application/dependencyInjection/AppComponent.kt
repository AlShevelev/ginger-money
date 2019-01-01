package com.syleiman.gingermoney.application.dependencyInjection

import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.application.dependencyInjection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.storages.db.typeConverters.MoneyTypeConverter
import dagger.Component

@Component(modules = [AppModule::class, AppModuleBinds::class])
@ApplicationScope
interface AppComponent {
    /**  */
    fun inject(app: App)

    /**  */
    fun inject(converter: MoneyTypeConverter)
}