package com.syleiman.gingermoney.core.storages.db.facade

import com.syleiman.gingermoney.core.global_entities.date_time.getEstimateValue
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.core.helpers.id.IdUtil
import com.syleiman.gingermoney.core.storages.db.core.DbCoreRun
import com.syleiman.gingermoney.core.storages.db.entities.AccountGroupSettingsDb
import com.syleiman.gingermoney.core.storages.db.mapping.map
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.AccountGroupSettings
import com.syleiman.gingermoney.dto.entities.Payment
import com.syleiman.gingermoney.dto.entities.PaymentCategory
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class DbStorageFacadeImpl
@Inject
constructor(
    private val db: DbCoreRun
) : DbStorageFacade {
    override fun updateSourceExchangeRates(sourceExchangeRates: List<ExchangeRate>) {
        db.runInTransaction { dbCore ->
            dbCore.sourceExchangeRate.deleteAll()
            dbCore.sourceExchangeRate.create(sourceExchangeRates.map { it.map() })
        }
    }

    override fun readSourceExchangeRates(): List<ExchangeRate> =
        db.run { dbCore ->
            dbCore.sourceExchangeRate.readAll().map { it.map() }
        }

    override fun readAccounts(): List<Account> =
        db.run { dbCore ->
            dbCore.accounts.readAll().map { it.map() }
        }

    override fun createAccount(account: Account) {
        db.run { dbCore ->
            dbCore.accounts.create(account.map())
        }
    }

    override fun updateAccount(account: Account) {
        db.run { dbCore ->
            dbCore.accounts.update(account.map())
        }
    }

    /**
     * Get account by its Db id
     */
    override fun readAccount(id: Long): Account? =
        db.run { dbCore ->
            dbCore.accounts.read(id)?.map()
        }

    /**
     * Returns true if an account has payments
     */
    override fun hasPayments(accountId: Long): Boolean =
        db.run { dbCore ->
            dbCore.payments.exists(accountId)
        }

    override fun readAccountGroupSettings(): List<AccountGroupSettings> =
        db.run { dbCore ->
            dbCore.accountGroupSettings.readAll().map { it.map() }
        }

    /**
     * [accountGroup] null in case of Total group
     */
    override fun updateAccountGroupSettings(accountGroup: AccountGroup?, currency: Currency) =
        db.runInTransaction { dbCore ->
            val record = dbCore.accountGroupSettings.readAll().firstOrNull { it.accountGroup == accountGroup }

            if(record == null) {
                dbCore.accountGroupSettings.create(
                    AccountGroupSettingsDb(IdUtil.generateLongId(), accountGroup, currency, null, null))
            } else {
                dbCore.accountGroupSettings.update(record.copy(currency = currency))
            }
        }

    /**
     * [accountGroup] null in case of Total group
     */
    override fun updateAccountGroupSettings(accountGroup: AccountGroup?, foregroundColor: Color, backgroundColor: Color) =
        db.runInTransaction { dbCore ->
            val record = dbCore.accountGroupSettings.readAll().firstOrNull { it.accountGroup == accountGroup }

            if(record == null) {
                dbCore.accountGroupSettings.create(
                    AccountGroupSettingsDb(IdUtil.generateLongId(), accountGroup, null, foregroundColor, backgroundColor))
            } else {
                dbCore.accountGroupSettings.update(record.copy(foregroundColor = foregroundColor, backgroundColor = backgroundColor))
            }
        }

    override fun readPaymentCategories(): List<PaymentCategory> =
        db.run { dbCore ->
            dbCore.paymentCategory.readAll().map { it.map() }
        }

    override fun readPaymentCategory(id: Long): PaymentCategory? =
        db.run { dbCore ->
            dbCore.paymentCategory.read(id)?.map()
        }

    override fun createPaymentCategory(category: PaymentCategory) {
        db.runInTransaction { dbCore ->
            dbCore.paymentCategory.create(category.map())
        }
    }

    override fun updatePaymentCategory(category: PaymentCategory) {
        db.runInTransaction { dbCore ->
            dbCore.paymentCategory.update(category.map())
        }
    }

    override fun createPayment(payment: Payment) {
        db.runInTransaction { dbCore ->
            dbCore.accounts.update(payment.account.map())
            dbCore.paymentCategory.update(payment.paymentCategory.map())
            dbCore.payments.create(payment.map())
        }
    }

    /**
     * Returns list of payments from Db
     * [from] - start time moment (included)
     * [to] - end time moment (excluded)
     */
    override fun readPayments(from: ZonedDateTime, to: ZonedDateTime): List<Payment> =
        db.run { dbCore ->
            dbCore.payments.read(from.getEstimateValue(), to.getEstimateValue()).map { it.map() }
        }
}