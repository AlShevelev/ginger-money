package com.syleiman.gingermoney.core.helpers.coroutines.dispatcherSource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Dispatcher for operations in Main thread
 */
class MainDispatcherSource: DispatcherSource {
    override val dispatcher: CoroutineDispatcher
        get() = Dispatchers.Main
}