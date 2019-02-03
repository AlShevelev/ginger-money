package com.syleiman.gingermoney.core.works.update_currency_rate

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 *
 */
class UpdateCurrencyRatesWorker(context : Context, params : WorkerParameters) : Worker(context, params) {
    /**
     *
     */
    override fun doWork(): Result {
        Log.d("WORKER_TEST", "UpdateCurrencyRatesWorker in [${Thread.currentThread().name}]")

        return Result.success()
    }
}