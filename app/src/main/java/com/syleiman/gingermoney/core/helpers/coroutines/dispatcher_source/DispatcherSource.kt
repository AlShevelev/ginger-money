package com.syleiman.gingermoney.core.helpers.coroutines.dispatcher_source

import kotlinx.coroutines.CoroutineDispatcher

/**
 *
 */
interface DispatcherSource {
    val dispatcher: CoroutineDispatcher
}