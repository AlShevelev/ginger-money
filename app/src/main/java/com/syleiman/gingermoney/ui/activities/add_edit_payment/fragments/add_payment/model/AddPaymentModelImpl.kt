package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model

import com.syleiman.gingermoney.core.global_entities.date_time.getEstimateValue
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateMatrix
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateSourceData
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.Payment
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.dto.entities.sortDate
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dto.AmountIsEmptyError
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.dto.CategoryIsEmptyError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.GeneralError
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

    override var accounts: List<Account> = listOf()
    private set

    override var categories: List<PaymentCategory> = listOf()
    private set

    override lateinit var selectedAccount: Account                // Selected account
    private set

    override var selectedCategory: PaymentCategory? = null       // Selected category
    private set

    override var selectedAmount: Money? = null                  // Selected amount

    override val memoMaxLen: Int = 64

    override suspend fun loadCategories(): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                categories = db.readPaymentCategories().sortedByDescending { it.sortDate }
                selectedCategory?.let { setSelectedCategory(it.id!!) }
                null

            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }

    override suspend fun loadAccounts(): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                accounts = db.readAccounts().sortedByDescending { it.sortDate }
                selectedAccount = accounts.first()
                null

            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }

    override fun getCreateAt(): ZonedDateTime = ZonedDateTime.now()

    override fun setSelectedAccount(id: Long): Account {
        selectedAccount =  accounts.first { it.id == id }
        return selectedAccount
    }

    override fun setSelectedCategory(id: Long): PaymentCategory {
        selectedCategory = categories.first { it.id == id }
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
                val accountNewAmount = selectedAccount.amount - convertAmountToAccountCurrency()

                // Create entities to save
                val accountToSave = selectedAccount.copy(amount = accountNewAmount, lastUsed = paymentCreateAt)
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
        val accountAmount = selectedAccount.amount

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