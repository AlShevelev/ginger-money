package com.syleiman.gingermoney.core.helpers.coroutines.dispatcherSource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Dispatcher for IO operations
 */
class IODispatcherSource: DispatcherSource {
    override val dispatcher: CoroutineDispatcher
        get() = Dispatchers.IO
}