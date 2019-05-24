package com.syleiman.gingermoney.core.works

import androidx.work.*
import com.syleiman.gingermoney.BuildConfig
import com.syleiman.gingermoney.core.works.update_currency_rate.UpdateCurrencyRatesWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorksManager
@Inject
constructor() : WorksManagerInterface {
    private companion object {
        private const val WORK_NAME = "${BuildConfig.APPLICATION_ID}.UPDATE_CURRENCY_RATES"
    }

    override fun startCurrencyRatesUpdates() {
        val updateConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val updateWork = PeriodicWorkRequestBuilder<UpdateCurrencyRatesWorker>(3, TimeUnit.HOURS)
            .setConstraints(updateConstraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, updateWork)
    }
}