package com.syleiman.gingermoney.ui.common.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syleiman.gingermoney.core.helpers.SingleLiveData
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ViewCommand
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class ViewModelBase<TModel : ModelBase> : ViewModel(), CoroutineScope {

    private val scopeJob: Job = SupervisorJob()

    /**
     * Context of this scope.
     */
    override val coroutineContext: CoroutineContext
        get() = scopeJob + Dispatchers.Main

    @Inject
    internal lateinit var model: TModel

    /**
     * Direct command for view
     */
    val command: SingleLiveData<ViewCommand> = SingleLiveData()

    /**
     * On configuration change we need to show dialog if it wasn't closed.
     * That's why we can't use [command]
     */
    val dialogCommands: MutableLiveData<ViewCommand> = MutableLiveData()

    protected fun <T>ModelCallResult<T>.processCallResult(normalAction: (T) -> Unit) =
        processCallResult(this, normalAction, {})

    protected fun <T>ModelCallResult<T>.processCallResult(normalAction: (T) -> Unit, errorAction: () -> Unit) =
        processCallResult(this, normalAction, errorAction)

    override fun onCleared() {
        super.onCleared()

        scopeJob.cancelChildren()
        scopeJob.cancel()
    }

    @JvmName("processCallResultInternal")
    private fun <T>processCallResult(callResult: ModelCallResult<T>, normalAction: (T) -> Unit, errorAction: () -> Unit) {
        callResult.value?.let { normalAction(it) }

        callResult.error?.also {
            command.value = ShowErrorCommand(it)
            errorAction()
        }
    }
}