package com.syleiman.gingermoney.core.works.update_currency_rate

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.application.dependency_injection.AppComponent
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateSourceData
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.utils.crashlytics.CrashlyticsUtils
import dagger.Lazy
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class UpdateCurrencyRatesWorker(
    context : Context,
    params : WorkerParameters
) : Worker(context, params) {

    companion object {
        private val RUB_USD_QUERY_KEY = "${Currency.USD}_${Currency.RUB}"
        private val EUR_USD_QUERY_KEY = "${Currency.USD}_${Currency.EUR}"
    }

    @Inject
    internal lateinit var crashlytics: Lazy<CrashlyticsUtils>

    @Inject
    internal lateinit var db: Lazy<DbStorageFacade>

    init {
        App.injections.get<AppComponent>().inject(this)
    }

    override fun doWork(): Result =
        processServerRequest()?.let { ratesFromServer ->
            parseRates(ratesFromServer)?.let { parsedRates ->
                storeRates(parsedRates)?.let {
                    Result.success()
                }
            }
        } ?: Result.retry()

    /**
     * Process rates request to a server and returns result as string
     * @return null in case of error
     */
    private fun processServerRequest(): String? {
        val baseUri = "https://free.currencyconverterapi.com/api/v6/convert"
        val apiKeyString = "apiKey=6e0cd2c3f42de678235c"
        val fullUri = "$baseUri?q=$RUB_USD_QUERY_KEY,$EUR_USD_QUERY_KEY&$apiKeyString"

        val url = URL(fullUri)
        var connection: HttpURLConnection? = null

        // We don't need to use Retrofit or ktor or "pure" okHttp for such simple case
        return try {
            connection = url.openConnection() as HttpURLConnection
            BufferedInputStream(connection.inputStream).use { it.reader().readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            crashlytics.get().log(ex)

            null
        }
        finally {
            connection?.disconnect()
        }
    }

    private fun parseRates(rates: String): List<ExchangeRate>? =
        try {
            val ratesResult = JSONObject(rates).getJSONObject("results")

            val rubToUsdRate = ratesResult.getJSONObject(RUB_USD_QUERY_KEY).getDouble("val")
            val eurToUsdRate = ratesResult.getJSONObject(EUR_USD_QUERY_KEY).getDouble("val")

            listOf(ExchangeRate(Currency.RUB, Currency.USD, rubToUsdRate), ExchangeRate(Currency.EUR, Currency.USD, eurToUsdRate))
        } catch (ex: JSONException) {
            ex.printStackTrace()
            crashlytics.get().log(ex)

            null
        }

    private fun storeRates(rates: List<ExchangeRate>): Any? =
        try {
            ExchangeRateSourceData(db.get()).updateRates(rates)
            true
        } catch (ex: Exception) {
            ex.printStackTrace()
            crashlytics.get().log(ex)

            null
        }
}