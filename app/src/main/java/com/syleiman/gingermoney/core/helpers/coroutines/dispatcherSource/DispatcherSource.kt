package com.syleiman.gingermoney.core.helpers.coroutines.dispatcherSource

import kotlinx.coroutines.CoroutineDispatcher

/**
 *
 */
interface DispatcherSource {
    val dispatcher: CoroutineDispatcher
}