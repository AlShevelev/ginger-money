package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.dto.errors.GroupIsEmptyError
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.*
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

open class AddAccountModelImpl
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacade,
    resourcesProvider: AppResourcesProvider,
    private val db: DbStorageFacade
) : ModelBaseImpl(),
    AddAccountModel {

    override val nameMaxLen: Int = resourcesProvider.getInt(R.integer.accountNameMaxLen)
    override val memoMaxLen: Int = resourcesProvider.getInt(R.integer.accountMemoMaxLen)

    /**
     * Returns current value of default selected Currency
     */
    override suspend fun getDefaultCurrency(): ModelCallResult<out Currency> =
        getValue {
            keyValueStorage.getDefaultCurrency()
        }

    override fun getAllAccountGroups(): List<AccountGroup> =
        listOf(
            AccountGroup.CASH,
            AccountGroup.CARDS,
            AccountGroup.CREDIT_CARDS,
            AccountGroup.DEBIT_CARDS,
            AccountGroup.ACCOUNTS,
            AccountGroup.DEPOSITS,
            AccountGroup.SAVINGS,
            AccountGroup.INVESTMENTS,
            AccountGroup.SHARES,
            AccountGroup.BONDS,
            AccountGroup.OTHER)

    override fun getAllCurrencies(): List<Currency> = listOf(Currency.USD, Currency.EUR, Currency.RUB)

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    @Suppress("IfThenToElvis")
    override suspend fun save(group: AccountGroup?, name: String?, amount: Money, memo: String?): DisplayingError? {
        val validationResult = validateGroup(group) ?: validateName(name) ?: validateMemo(memo)

        return if(validationResult != null) {
            validationResult
        } else {
            withContext(Dispatchers.IO) {
                try {
                    db.createAccount(Account(null, group!!, name!!, amount, memo, ZonedDateTime.now(), null))
                    null
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    GeneralError()
                }
            }
        }
    }

    protected fun validateGroup(group: AccountGroup?): DisplayingError? = if(group != null) null else GroupIsEmptyError()

    protected fun validateName(name: String?): DisplayingError? =
        when {
            name.isNullOrEmpty() || name.isNullOrBlank() -> NameIsEmptyError()
            name.length > nameMaxLen -> NameIsTooLongError()
            else -> null
        }

    protected fun validateMemo(memo: String?) = memo?.takeIf { it.length > memoMaxLen }?.let { MemoIsTooLongError() }
}