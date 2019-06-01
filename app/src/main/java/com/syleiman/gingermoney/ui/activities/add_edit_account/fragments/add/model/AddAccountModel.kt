package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model

import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dto.errors.GroupIsEmpty
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dto.errors.MemoIsTooLong
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dto.errors.NameIsEmpty
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.dto.errors.NameIsTooLong
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddAccountModel
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacadeInterface,
    resourcesProvider: AppResourcesProviderInterface,
    private val db: DbStorageFacadeInterface
) : ModelBase(),
    AddAccountModelInterface {

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
                    db.addAccount(Account(null, group!!, name!!, amount, memo))
                    null
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    GeneralError()
                }
            }
        }
    }

    private fun validateGroup(group: AccountGroup?): DisplayingError? = if(group != null) null else GroupIsEmpty()

    private fun validateName(name: String?): DisplayingError? =
        when {
            name.isNullOrEmpty() || name.isNullOrBlank() -> NameIsEmpty()
            name.length > nameMaxLen -> NameIsTooLong()
            else -> null
        }

    private fun validateMemo(memo: String?) = memo?.takeIf { it.length > memoMaxLen }?.let { MemoIsTooLong() }
}