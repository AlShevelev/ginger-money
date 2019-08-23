package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import org.threeten.bp.ZonedDateTime

interface AddPaymentModel: ModelBase {
    val accounts: List<Account>

    val categories: List<PaymentCategory>

    val selectedAccount: Account
    var selectedAmount: Money?

    val memoMaxLen: Int

    fun getCreateAt(): ZonedDateTime

    suspend fun loadCategories(): DisplayingError?

    suspend fun loadAccounts(): DisplayingError?

    fun setSelectedAccount(id: Long): Account

    fun setSelectedCategory(id: Long): PaymentCategory

    suspend fun getDefaultCurrency(): ModelCallResult<out Currency>

    fun getAllCurrencies(): List<Currency>

    suspend fun save(createAt: ZonedDateTime, memo: String?): DisplayingError?
}