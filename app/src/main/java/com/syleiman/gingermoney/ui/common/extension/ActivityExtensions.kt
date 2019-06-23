package com.syleiman.gingermoney.ui.common.extension

import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderImpl
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider

/**
 * Provides access to [AppResourcesProviderImpl] for activity.
 * This property should be used in biding adapters only!
 */
val AppCompatActivity.appResourcesProvider: AppResourcesProvider
get() = AppResourcesProviderImpl(this)