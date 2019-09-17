package com.syleiman.gingermoney.ui.activities.main.fragments.payments.model

import com.syleiman.gingermoney.core.global_entities.date_time.ZonedDateTimeUtil
import com.syleiman.gingermoney.core.global_entities.date_time.isWeekend
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateMatrix
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateSourceData
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.helpers.id.IdUtil
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.dto.entities.Payment
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.*
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import org.threeten.bp.DayOfWeek
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class PaymentsModelImpl
@Inject
constructor(
    private val db: DbStorageFacade,
    private val keyValueStorage: KeyValueStorageFacade
) : ModelBaseImpl(),
    PaymentsModel {

    override var currentPeriod: PaymentsListPeriodInfo = PaymentsListPeriodInfo(ZonedDateTime.now(), true)
        private set

    override suspend fun getPaymentsList(): ModelCallResult<out PaymentsList> =
        getValue {
            val hasAccounts = db.readAccounts().isNotEmpty()

            if(!hasAccounts) {
                return@getValue PaymentsList(false, listOf())
            } else {
                val start = with(currentPeriod.dateInPeriod) { ZonedDateTimeUtil.createZonedDateTime(year, monthValue, 1) }
                val end = start.plusMonths(1)

                val payments = db.readPayments(start, end)

                if(payments.isEmpty()) {
                    return@getValue PaymentsList(true, listOf())
                } else {
                    val sourceExchangeRates = ExchangeRateSourceData(db)
                    val exchangeMatrix = ExchangeRateMatrix(sourceExchangeRates.getRates())
                    val defaultCurrency = keyValueStorage.getDefaultCurrency()!!

                    val startDayOfWeek = keyValueStorage.getStartDayOfWeek() ?: DayOfWeek.MONDAY

                    val result = mutableListOf<ListItem>()

                    // Total list item is added here
                    result.add(TotalListItem(IdUtil.generateLongId(), calculateTotal(payments, exchangeMatrix, defaultCurrency)))

                    payments
                        .sortedByDescending { it.createAt }
                        .groupBy { it.createAt.dayOfMonth }
                        .forEach {
                            val paymentsInDay = it.value

                            if(paymentsInDay.isNotEmpty()) {
                                var dayTotalAmount = defaultCurrency.toMoney(0L)

                                val paymentsInDayListItems = mutableListOf<ListItem>()

                                paymentsInDay.forEach { payment ->
                                    val paymentRate = exchangeMatrix.getExchangeRate(payment.amount.currency, defaultCurrency)
                                    dayTotalAmount += payment.amount.convertTo(paymentRate)

                                    paymentsInDayListItems.add(PaymentListItem(
                                        payment.id!!,
                                        payment.paymentCategory.name,
                                        payment.account.name,
                                        payment.amount))
                                }

                                result.add(DayListItem(
                                    IdUtil.generateLongId(),
                                    paymentsInDay.first().createAt,
                                    paymentsInDay.first().createAt.isWeekend(startDayOfWeek),
                                    dayTotalAmount))

                                result.addAll(paymentsInDayListItems)
                            }
                        }

                    return@getValue PaymentsList(true, result)
                }
            }
        }

    override fun moveToNextPeriod(): PaymentsListPeriodInfo {
        val current = currentPeriod.dateInPeriod
        val now = ZonedDateTime.now()

        if(now.monthValue == current.monthValue && now.year == current.year) {
            return currentPeriod
        }

        val newCurrent = currentPeriod.dateInPeriod.plusMonths(1)
        val isLastPeriod = now.monthValue == newCurrent.monthValue && now.year == newCurrent.year

        currentPeriod = PaymentsListPeriodInfo(newCurrent, isLastPeriod)
        return currentPeriod
    }

    override fun moveToPriorPeriod(): PaymentsListPeriodInfo {
        currentPeriod = PaymentsListPeriodInfo(currentPeriod.dateInPeriod.minusMonths(1), false)
        return currentPeriod
    }

    private fun calculateTotal(payments: List<Payment>, exchangeMatrix: ExchangeRateMatrix, currency: Currency): Money {
        var totalAmount = currency.toMoney(0L)

        payments.forEach {
            val totalRate = exchangeMatrix.getExchangeRate(it.amount.currency, totalAmount.currency)
            totalAmount += it.amount.convertTo(totalRate)
        }

        return totalAmount
    }
}