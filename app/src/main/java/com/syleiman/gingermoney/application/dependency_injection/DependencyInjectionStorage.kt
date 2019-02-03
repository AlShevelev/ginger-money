package com.syleiman.gingermoney.application.dependency_injection

import android.content.Context
import com.syleiman.gingermoney.ui.activities.login.dependency_injection.LoginActivityComponent
import com.syleiman.gingermoney.ui.dependency_injection.UIComponent
import com.syleiman.gingermoney.ui.activities.root.dependency_injection.RootActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.dependency_injection.SetupActivityComponent
import kotlin.reflect.KClass

/** Storage for Dagger components on application level  */
class DependencyInjectionStorage(private val appContext: Context) {
    private val components = mutableMapOf<KClass<*>, Any>()

    inline fun <reified T>get(vararg args: Any): T = getComponent(T::class, args)

    inline fun <reified T>release() = releaseComponent(T::class)

    @Suppress("UNCHECKED_CAST")
    fun <T>getComponent(type: KClass<*>, args: Array<out Any>): T {
        var result = components[type]
        if(result == null) {
            result = provideComponent<T>(type, args)
            components[type] = result!!
        }
        return result as T
    }

    fun releaseComponent(type: KClass<*>) = components.remove(type)

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST", "DEPRECATION")
    private fun <T>provideComponent(type: KClass<*>, args: Array<out Any>): T {
        return when(type) {
            AppComponent::class -> DaggerAppComponent.builder().appModule(AppModule(appContext)).build()
            UIComponent::class -> get<AppComponent>().ui.build()
            RootActivityComponent::class -> get<UIComponent>().rootActivity.build()
            SetupActivityComponent::class -> get<UIComponent>().setupActivity.build()
            LoginActivityComponent::class -> get<UIComponent>().loginActivity.build()

            else -> throw UnsupportedOperationException("This component is not supported: ${type.simpleName}")
        } as T
    }
}