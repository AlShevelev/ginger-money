package com.syleiman.gingermoney.ui.common.mvvm

import androidx.annotation.CallSuper
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface

/**
 *
 */
abstract class ModelBase (protected val launchManager: MainLaunchManagerInterface) : ModelBaseInterface {
    /**
     *
     */
    @CallSuper
    override fun cancelBackgroundOperations() = launchManager.cancel()
}