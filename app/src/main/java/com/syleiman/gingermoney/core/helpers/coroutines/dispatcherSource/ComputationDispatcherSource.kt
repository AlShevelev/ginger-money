package com.syleiman.gingermoney.core.helpers.coroutines.dispatcherSource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Dispatcher for intensive computations operations
 */
class ComputationDispatcherSource: DispatcherSource {
    override val dispatcher: CoroutineDispatcher
        get() = Dispatchers.Default
}