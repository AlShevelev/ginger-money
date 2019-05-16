package com.syleiman.gingermoney.ui.common.extension

import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface

/**
 * Provides access to [AppResourcesProvider] for activity.
 * This property should be used in biding adapters only!
 */
val AppCompatActivity.appResourcesProvider: AppResourcesProviderInterface
get() = AppResourcesProvider(this)