package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 */
class AccountsModel
@Inject
constructor(
    private val db: DbStorageFacadeInterface
) : AccountsModelInterface {

    /**
     *
     */
    override suspend fun getAllAccounts(): ModelCallResult<out List<Account>> =
        withContext(Dispatchers.IO) {
            try {
                ModelCallResult(null, db.getAccounts())
            } catch (ex: Exception) {
                ex.printStackTrace()
                ModelCallResult(GeneralError(), null)
            }
        }
}