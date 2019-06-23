package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.edit.model

import com.syleiman.gingermoney.application.dependency_injection.Clarification
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model.AddAccountModelImpl
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class EditAccountModelImpl
@Inject
constructor(
    @Named(Clarification.ACCOUNT_DB_ID) private val accountDbId: Long,
    keyValueStorage: KeyValueStorageFacade,
    resourcesProvider: AppResourcesProvider,
    private val db: DbStorageFacade
) : AddAccountModelImpl(
        keyValueStorage,
        resourcesProvider,
        db
),  EditAccountModel {

    override suspend fun getAccount(): ModelCallResult<out Account> =
        getValue {
            db.readAccount(accountDbId)!!
        }

    override suspend fun canUpdateCurrency(): ModelCallResult<out Boolean> =
        getValue {
            !db.hasExpenses(accountDbId)
        }

    override suspend fun save(group: AccountGroup?, name: String?, amount: Money, memo: String?): DisplayingError? {
        val validationResult = validateGroup(group) ?: validateName(name) ?: validateMemo(memo)

        return if(validationResult != null) {
            validationResult
        } else {
            withContext(Dispatchers.IO) {
                try {
                    db.updateAccount(Account(accountDbId, group!!, name!!, amount, memo))
                    null
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    GeneralError()
                }
            }
        }
    }
}