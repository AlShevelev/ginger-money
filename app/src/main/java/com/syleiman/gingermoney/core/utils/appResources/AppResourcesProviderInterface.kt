package com.syleiman.gingermoney.core.utils.appResources

import androidx.annotation.IntegerRes
import androidx.annotation.StringRes

interface AppResourcesProviderInterface {
    /**
     *
     */
    fun getLocale(): String

    /**
     *
     */
    fun getString(@StringRes resId: Int): String

    /**
     *
     */
    fun getInt(@IntegerRes resId: Int): Int

    /**
     * Get metadata value as string
     */
    fun getMetadataValue(key: String): Any?
}