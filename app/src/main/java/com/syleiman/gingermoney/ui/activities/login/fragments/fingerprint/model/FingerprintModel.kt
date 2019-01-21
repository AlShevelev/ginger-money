package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model

import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import javax.inject.Inject

/**
 *
 */
class FingerprintModel
@Inject
constructor(
    launchManager: MainLaunchManagerInterface
) : ModelBase(launchManager),
    FingerprintModelInterface {

}