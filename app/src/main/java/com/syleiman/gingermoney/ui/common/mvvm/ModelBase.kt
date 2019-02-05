package com.syleiman.gingermoney.ui.common.mvvm

import androidx.annotation.CallSuper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 *
 */
abstract class ModelBase : CoroutineScope, ModelBaseInterface {

    private val scopeJob: Job = SupervisorJob()

    /**
     * Context of this scope.
     */
    override val coroutineContext: CoroutineContext
        get() = scopeJob + Dispatchers.Main

    /**
     *
     */
    @CallSuper
    override fun cancelBackgroundOperations() {
        scopeJob.cancelChildren()
        scopeJob.cancel()
    }
}