package com.syleiman.gingermoney.core.helpers.coroutines.managers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import com.syleiman.gingermoney.core.helpers.coroutines.dispatcher_source.DispatcherSource
import kotlin.coroutines.CoroutineContext

/**
 * Base class for launching coroutines in local UI scope
 */
abstract class LaunchManagerBase(
    private val dispatcherSource: DispatcherSource,
    initOnStart: Boolean = true
) : CoroutineScope,
    LaunchManagerBaseInterface {

    private var scopeJob: Job? = null

    var isCanceled = !initOnStart
    private set

    init {
        if(initOnStart) {
            scopeJob = SupervisorJob()
            isCanceled = false
        }
    }

    /**
     * Context of this scope.
     */
    override val coroutineContext: CoroutineContext
        get() = scopeJob!! + dispatcherSource.dispatcher

    /**
     *
     */
    override fun restart() {
        if(isCanceled) {
            scopeJob = SupervisorJob()
            isCanceled = false
        }
    }

    /**
     *
     */
    override fun cancel() {
        scopeJob?.also {
            it.cancelChildren()
            it.cancel()
        }

        isCanceled = true
        scopeJob = null
    }

    /**
     *
     */
    override fun cancelAndRestart() {
        cancel()
        restart()
    }
}