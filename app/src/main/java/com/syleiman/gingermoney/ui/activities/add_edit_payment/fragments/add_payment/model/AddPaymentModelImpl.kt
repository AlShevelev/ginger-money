package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class AddPaymentModelImpl
@Inject
constructor(
    private val db: DbStorageFacade
) : ModelBaseImpl(),
    AddPaymentModel {

    private lateinit var _accounts: List<Account>
    override val accounts: List<Account>
    get() = _accounts

    private lateinit var _paymentCategories: List<PaymentCategory>
    override val paymentCategories: List<PaymentCategory>
    get() = _paymentCategories

    private var account: Account? = null    // Selected account

    override suspend fun loadData(): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                _accounts = db.readAccounts()
                _paymentCategories = db.readPaymentCategories()
                null

            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }

    override fun getCreateAt(): ZonedDateTime = ZonedDateTime.now()

    override fun setSelectedAccount(id: Long): Account {
        account =  _accounts.first { it.id == id }
        return account!!
    }
}