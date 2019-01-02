package com.syleiman.gingermoney.core.helpers.coroutines.managers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.syleiman.gingermoney.core.helpers.coroutines.dispatcherSource.MainDispatcherSource
import javax.inject.Inject

/**
 *
 */
class MainLaunchManager
@Inject
constructor() : LaunchManagerBase(MainDispatcherSource()), MainLaunchManagerInterface {
    /**
     * Launches [action] in background thread and pass the result to UI thread
     * @param resultCallback returns result of [action] or null in case of exception
     */
    override fun <T> launchFromUI(resultCallback: (T?) -> Unit, action: suspend () -> T?) =
        launchFromUI(resultCallback, null, action)

    /**
     * Launches [action] in background thread and pass the result to UI thread
     * @param resultCallback returns result of [action] or [failValue] in case of exception
     */
    override fun <T> launchFromUI(resultCallback: (T) -> Unit, failValue: T, action: suspend () -> T) {
        if (isCanceled) {
            return
        }

        launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    action()
                }
                if (isActive) {
                    resultCallback(result)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isActive) {
                    resultCallback(failValue)
                }
            }
        }
    }

    /**
     * Launches [action] in background thread and calls [resultCallback] when [action] is completed or case of exception
     */
    override fun launchFromUI(resultCallback: () -> Unit, action: suspend () -> Unit) {
        if (isCanceled) {
            return
        }

        launch {
            try {
                withContext(Dispatchers.IO) {
                    action()
                }
                if (isActive) {
                    resultCallback()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isActive) {
                    resultCallback()
                }
            }
        }
    }


    /**
     * Launches [action] in background thread and pass the result to UI thread
     * @param resultCallback return null or [Exception] in case of exception
     */
    override fun launchFromUIWithException(resultCallback: (Exception?) -> Unit, action: suspend () -> Unit) {
        if(isCanceled) {
            return
        }

        launch {
            try {
                withContext(Dispatchers.IO) {
                    action()
                }
                if (isActive) {
                    resultCallback(null)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isActive) {
                    resultCallback(ex)
                }
            }
        }
    }

    /**
     * Launches [action] in background thread and pass the result to UI thread
     * @param resultCallback returns result of [action] or [Exception] in case of exception
     */
    override fun <T> launchFromUIWithException(resultCallback: (T?, Exception?) -> Unit, action: suspend () -> T?) {
        if(isCanceled) {
            return
        }

        launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    action()
                }
                if (isActive) {
                    resultCallback(result, null)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (isActive) {
                    resultCallback(null, ex)
                }
            }
        }
    }
}