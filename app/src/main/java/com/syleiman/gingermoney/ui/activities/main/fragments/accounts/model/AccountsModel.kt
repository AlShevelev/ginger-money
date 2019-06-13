package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateMatrix
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateSourceData
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.AccountListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.GroupListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.TotalListItem
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import javax.inject.Inject

class AccountsModel
@Inject
constructor(
    private val db: DbStorageFacadeInterface,
    private val keyValueStorage: KeyValueStorageFacadeInterface
) : ModelBase(),
    AccountsModelInterface {

    override suspend fun getListItems(): ModelCallResult<out List<ListItem>> =
        getValue {
            val defaultCurrency = keyValueStorage.getDefaultCurrency()
            val sourceExchangeRates = ExchangeRateSourceData(db)
            val exchangeMatrix = ExchangeRateMatrix(sourceExchangeRates.getRates())

            val dbAccounts = db.readAccounts()

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
                    result.add(GroupListItem(group.value.toLong(), group, groupAmount, Color.YELLOW, Color.BLACK))
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

    private fun calculateHeadersSettings(defaultCurrency: Currency, usedGroups: List<AccountGroup>): ListItemsSettings {
        val dbGroupsSettings = db.readAccountGroupSettings()

        val totalCurrency = dbGroupsSettings.firstOrNull { it.accountGroup == null }?.currency ?: defaultCurrency

        val groupsSettings = mutableMapOf<AccountGroup,GroupListItemSettings>()

        usedGroups.forEach { group ->
            val dbGroupSettings = dbGroupsSettings.firstOrNull { it.accountGroup == group }

            groupsSettings[group] = if(dbGroupSettings == null) {
                GroupListItemSettings(defaultCurrency, Color.BLACK, Color.YELLOW)
            } else {
                GroupListItemSettings(
                    dbGroupSettings.currency ?: defaultCurrency,
                    dbGroupSettings.foregroundColor ?: Color.BLACK,
                    dbGroupSettings.backgroundColor ?: Color.YELLOW)
            }
        }

        return ListItemsSettings(totalCurrency, groupsSettings)
    }
}