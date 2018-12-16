package com.syleiman.gingermoney.core.utils.appResources

import androidx.annotation.StringRes

interface AppResourcesProviderInterface {
    /** */
    fun getLocale(): String

    /** */
    fun getString(@StringRes resId: Int): String

    /** Get metadata value as string */
    fun getMetadataValue(key: String): Any?
}