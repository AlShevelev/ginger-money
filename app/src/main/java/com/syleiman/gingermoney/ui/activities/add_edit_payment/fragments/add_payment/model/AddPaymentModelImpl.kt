package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model

import com.syleiman.gingermoney.core.global_entities.date_time.getEstimateValue
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateMatrix
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateSourceData
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.helpers.id.IdUtil
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.Payment
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.dto.entities.sortDate
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dto.AccountIsEmptyError
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dto.AmountIsEmptyError
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dto.CategoryIsEmptyError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class AddPaymentModelImpl
@Inject
constructor(
    private val db: DbStorageFacade,
    private val keyValueStorage: KeyValueStorageFacade

) : ModelBaseImpl(),
    AddPaymentModel {

    private lateinit var _accounts: List<Account>
    override val accounts: List<Account>
    get() = _accounts

    private lateinit var _categories: List<PaymentCategory>
    override val categories: List<PaymentCategory>
    get() = _categories

    private var selectedAccount: Account? = null                // Selected account
    private var selectedCategory: PaymentCategory? = null       // Selected category
    override var selectedAmount: Money? = null                  // Selected amount

    override val memoMaxLen: Int = 64

    override suspend fun loadData(): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                _accounts = db.readAccounts().sortedByDescending { it.sortDate }
                _categories = db.readPaymentCategories().sortedByDescending { it.sortDate }
                null

            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }

    override fun getCreateAt(): ZonedDateTime = ZonedDateTime.now()

    override fun setSelectedAccount(id: Long): Account {
        selectedAccount =  _accounts.first { it.id == id }
        return selectedAccount!!
    }

    override fun setSelectedCategory(id: Long): PaymentCategory {
        selectedCategory = _categories.first { it.id == id }
        return selectedCategory!!
    }

    override suspend fun getDefaultCurrency(): ModelCallResult<out Currency> =
        getValue {
            keyValueStorage.getDefaultCurrency()
        }

    override fun getAllCurrencies(): List<Currency> = listOf(Currency.USD, Currency.EUR, Currency.RUB)

    override suspend fun save(createAt: ZonedDateTime, memo: String?): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                // Validation
                val validationResult = validatePayment()
                if(validationResult != null) {
                    return@withContext validationResult
                }

                // CreateAt value correction
                val paymentCreateAt = correctCreateAt(createAt)

                // Calculate amount on the account
                val accountNewAmount = selectedAccount!!.amount - convertAmountToAccountCurrency()

                // Create entities to save
                val accountToSave = selectedAccount!!.copy(amount = accountNewAmount, lastUsed = paymentCreateAt)
                val categoryToSave = selectedCategory!!.copy(lastUsed = paymentCreateAt)
                val paymentToSave = Payment(
                    null,
                    accountToSave,
                    categoryToSave,
                    selectedAmount!!,
                    memo,
                    paymentCreateAt,
                    paymentCreateAt.getEstimateValue())

                // Saving
                db.createPayment(paymentToSave)

                null
            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }

    private fun validatePayment(): DisplayingError? =
        when {
            selectedAccount == null -> AccountIsEmptyError()
            selectedCategory == null -> CategoryIsEmptyError()
            selectedAmount?.totalCents ?: 0L == 0L -> AmountIsEmptyError()
            else -> null
        }

    private fun correctCreateAt(createAt: ZonedDateTime): ZonedDateTime =
        ZonedDateTime.now().let { now ->
            if(createAt > now) now else createAt
        }

    private fun convertAmountToAccountCurrency(): Money {
        val paymentAmount = selectedAmount!!
        val accountAmount = selectedAccount!!.amount

        return if (accountAmount.currency == paymentAmount.currency) {
            paymentAmount
        } else {
            val sourceExchangeRates = ExchangeRateSourceData(db)
            val exchangeMatrix = ExchangeRateMatrix(sourceExchangeRates.getRates())

            val rate = exchangeMatrix.getExchangeRate(paymentAmount.currency, accountAmount.currency)
            paymentAmount.convertTo(rate)
        }
    }
}