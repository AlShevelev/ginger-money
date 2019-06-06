package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.global_entities.money.ExchangeRateMatrix
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
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
            val exchangeRates = db.getSourceExchangeRates()
            val exchangeMatrix = ExchangeRateMatrix(defaultCurrency!!, exchangeRates)

            val result = mutableListOf<ListItem>()

            var totalAmount = defaultCurrency.toMoney(0L)
            result.add(TotalListItem(-1, totalAmount))

            db.getAccounts()
                .sortedBy { it.accountGroup.value }
                .groupBy { it.accountGroup }
                .map { (group, accounts) ->
                    var groupAmount = defaultCurrency.toMoney(0L)
                    result.add(GroupListItem(group.value.toLong(), group, groupAmount, Color.BLUE, Color.BLACK))
                    val groupIndex = result.lastIndex

                    accounts
                        .sortedBy { it.name }
                        .forEach { account ->
                            val rate = exchangeMatrix.getExchangeRate(account.amount.currency, defaultCurrency)

                            totalAmount += account.amount.convertTo(rate)
                            groupAmount += account.amount.convertTo(rate)

                            result.add(AccountListItem(account.id!!, account.id!!, account.name, account.amount))
                        }
                    result[groupIndex] = (result[groupIndex] as GroupListItem).copy(amount = groupAmount)
                }

            result[0] = (result[0] as TotalListItem).copy(amount = totalAmount)

            result
        }
}