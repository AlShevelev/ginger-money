package com.syleiman.gingermoney.application.dependencyInjection

import android.content.Context
import com.syleiman.gingermoney.application.dependencyInjection.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

/** Application level module - global objects are created here   */
@Module
class AppModule(private val appContext: Context) {
    /**  */
    @Provides
    @ApplicationScope
    internal fun provideContext(): Context = appContext
}