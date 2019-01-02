package com.syleiman.gingermoney.core.helpers.coroutines.managers

/**
 *
 */
interface LaunchManagerBaseInterface {
    /**
     *
     */
    fun restart()

    /**
     *
     */
    fun cancel()

    /**
     *
     */
    fun cancelAndRestart()
}