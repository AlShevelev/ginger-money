package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model

import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import org.threeten.bp.ZonedDateTime

interface AddPaymentModel: ModelBase {
    val accounts: List<Account>

    val paymentCategories: List<PaymentCategory>

    fun getCreateAt(): ZonedDateTime

    suspend fun loadData(): DisplayingError?

    /**
     * Memorizes an account with [id] and returns it
     */
    fun setSelectedAccount(id: Long): Account
}