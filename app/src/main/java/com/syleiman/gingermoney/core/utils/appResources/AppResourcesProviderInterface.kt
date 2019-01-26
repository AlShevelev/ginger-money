package com.syleiman.gingermoney.core.utils.appResources

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
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
     * Get string and format it
     */
    fun getFormattedString(@StringRes resId: Int, vararg args: Any): String

    /**
     *
     */
    fun getInt(@IntegerRes resId: Int): Int

    /**
     *
     */
    @ColorInt
    fun getColor(@ColorRes resId: Int): Int

    /**
     * Get metadata value as string
     */
    fun getMetadataValue(key: String): Any?
}