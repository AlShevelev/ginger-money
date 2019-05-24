package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import javax.inject.Inject

class AccountsModel
@Inject
constructor(
    private val db: DbStorageFacadeInterface
) : ModelBase(),
    AccountsModelInterface {

    override suspend fun getAllAccounts(): ModelCallResult<out List<Account>> =
        getValue {
            db.getAccounts()
        }
}