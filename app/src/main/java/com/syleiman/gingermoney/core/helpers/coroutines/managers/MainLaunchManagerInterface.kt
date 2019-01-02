package com.syleiman.gingermoney.core.helpers.coroutines.managers

interface MainLaunchManagerInterface : LaunchManagerBaseInterface {
    /**
     * Launches [action] in background thread and pass the result to UI thread
     * @param resultCallback returns result of [action] or null in case of exception
     */
    fun <T> launchFromUI(resultCallback: (T?) -> Unit, action: suspend () -> T?)

    /**
     * Launches [action] in background thread and pass the result to UI thread
     * @param resultCallback returns result of [action] or [failValue] in case of exception
     */
    fun <T> launchFromUI(resultCallback: (T) -> Unit, failValue: T, action: suspend () -> T)

    /**
     * Launches [action] in background thread and calls [resultCallback] when [action] is completed or case of exception
     */
    fun launchFromUI(resultCallback: () -> Unit, action: suspend () -> Unit)

    /**
     * Launches [action] in background thread and pass the result to UI thread
     * @param resultCallback return null or [Exception] in case of exception
     */
    fun launchFromUIWithException(resultCallback: (Exception?) -> Unit, action: suspend () -> Unit)

    /**
     * Launches [action] in background thread and pass the result to UI thread
     * @param resultCallback returns result of [action] or [Exception] in case of exception
     */
    fun <T> launchFromUIWithException(resultCallback: (T?, Exception?) -> Unit, action: suspend () -> T?)
}