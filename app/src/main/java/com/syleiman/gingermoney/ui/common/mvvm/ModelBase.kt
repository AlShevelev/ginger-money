package com.syleiman.gingermoney.ui.common.mvvm

import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface

/**
 *
 */
abstract class ModelBase (protected val launchManager: MainLaunchManagerInterface) : ModelBaseInterface {
    /**
     *
     */
    override fun cancelBackgroundOperations() = launchManager.cancel()
}