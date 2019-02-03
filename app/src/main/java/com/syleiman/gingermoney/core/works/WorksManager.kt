package com.syleiman.gingermoney.core.works

import androidx.work.*
import com.syleiman.gingermoney.core.works.update_currency_rate.UpdateCurrencyRatesWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *
 */
class WorksManager
@Inject
constructor() : WorksManagerInterface {

    /**
     *
     */
    override fun startCurrencyRatesUpdates() {
        val updateConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val updateWork = PeriodicWorkRequestBuilder<UpdateCurrencyRatesWorker>(1, TimeUnit.MINUTES)
            .setConstraints(updateConstraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork("UPDATE_CURRENCY_RATES", ExistingPeriodicWorkPolicy.KEEP, updateWork)
    }
}