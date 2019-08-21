package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateMatrix
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateSourceData
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.AccountListItem
import com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor.TextColors
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.GroupListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.TotalListItem
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountsModelImpl
@Inject
constructor(
    private val db: DbStorageFacade,
    private val keyValueStorage: KeyValueStorageFacade
) : ModelBaseImpl(),
    AccountsModel {

    private val defaultGroupTextColor = Color.BLACK
    private val defaultGroupBackgroundColor = Color.YELLOW

    override suspend fun getAccountsList(): ModelCallResult<out List<ListItem>> =
        getValue {
            val defaultCurrency = keyValueStorage.getDefaultCurrency()
            val sourceExchangeRates = ExchangeRateSourceData(db)
            val exchangeMatrix = ExchangeRateMatrix(sourceExchangeRates.getRates())

            val dbAccounts = db.readAccounts()

            if(dbAccounts.isEmpty()) {
                return@getValue listOf<ListItem>()
            }

            val allUsedGroups = dbAccounts.map { it.accountGroup }.distinct()
            val headersSettings = calculateHeadersSettings(defaultCurrency!!, allUsedGroups)

            val result = mutableListOf<ListItem>()

            var totalAmount = headersSettings.totalCurrency.toMoney(0L)
            result.add(TotalListItem(-1, totalAmount))

            dbAccounts
                .sortedBy { it.accountGroup.value }
                .groupBy { it.accountGroup }
                .map { (group, accounts) ->
                    val groupSettings = headersSettings.groupsSettings[group]!!

                    var groupAmount = groupSettings.currency.toMoney(0L)
                    result.add(GroupListItem(group.value.toLong(), group, groupAmount, groupSettings.colors))
                    val groupIndex = result.lastIndex

                    accounts
                        .sortedBy { it.name }
                        .forEach { account ->
                            val totalRate = exchangeMatrix.getExchangeRate(account.amount.currency, headersSettings.totalCurrency)
                            val groupRate = exchangeMatrix.getExchangeRate(account.amount.currency, groupSettings.currency)

                            totalAmount += account.amount.convertTo(totalRate)
                            groupAmount += account.amount.convertTo(groupRate)

                            result.add(AccountListItem(account.id!!, account.id!!, account.name, account.amount))
                        }
                    result[groupIndex] = (result[groupIndex] as GroupListItem).copy(amount = groupAmount)
                }

            result[0] = (result[0] as TotalListItem).copy(amount = totalAmount)

            result
        }

    override suspend fun getCurrency(group: AccountGroup?): Currency =
        withContext(Dispatchers.IO) {
            try {
                db.readAccountGroupSettings()
                    .firstOrNull { it.accountGroup == group }?.currency
                    ?: keyValueStorage.getDefaultCurrency()!!
            } catch (ex: Exception) {
                ex.printStackTrace()
                keyValueStorage.getDefaultCurrency()!!
            }
        }

    override suspend fun getColors(group: AccountGroup): ModelCallResult<out TextColors> =
        getValue {
            db.readAccountGroupSettings()
                .firstOrNull { it.accountGroup == group }
                ?.let {
                    TextColors(
                        it.foregroundColor ?: defaultGroupTextColor, it.backgroundColor ?: defaultGroupBackgroundColor
                    )
                }
                ?: TextColors(
                    defaultGroupTextColor,
                    defaultGroupBackgroundColor
                )
        }

    override suspend fun updateCurrency(group: AccountGroup?, currency: Currency): DisplayingError? =
        saveValue {
            db.updateAccountGroupSettings(group, currency)
        }

    override suspend fun updateColors(group: AccountGroup, colors: TextColors): DisplayingError? =
        saveValue {
            db.updateAccountGroupSettings(group, colors.foregroundColor, colors.backgroundColor)
        }

    private fun calculateHeadersSettings(defaultCurrency: Currency, usedGroups: List<AccountGroup>): ListItemsSettings {
        val dbGroupsSettings = db.readAccountGroupSettings()

        val totalCurrency = dbGroupsSettings.firstOrNull { it.accountGroup == null }?.currency ?: defaultCurrency

        val groupsSettings = mutableMapOf<AccountGroup,GroupListItemSettings>()

        usedGroups.forEach { group ->
            val dbGroupSettings = dbGroupsSettings.firstOrNull { it.accountGroup == group }

            groupsSettings[group] = if(dbGroupSettings == null) {
                GroupListItemSettings(defaultCurrency,
                    TextColors(
                        defaultGroupTextColor,
                        defaultGroupBackgroundColor
                    )
                )
            } else {
                GroupListItemSettings(
                    dbGroupSettings.currency ?: defaultCurrency,
                    TextColors(
                        dbGroupSettings.foregroundColor ?: defaultGroupTextColor,
                        dbGroupSettings.backgroundColor ?: defaultGroupBackgroundColor
                    )
                )
            }
        }

        return ListItemsSettings(totalCurrency, groupsSettings)
    }
}