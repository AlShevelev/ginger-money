package com.syleiman.gingermoney.core.storages.db.typeConverters

import androidx.room.TypeConverter
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.application.dependencyInjection.AppComponent
import com.syleiman.gingermoney.core.globalEntities.money.Currency
import com.syleiman.gingermoney.core.globalEntities.money.Money
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import java.nio.ByteBuffer
import javax.inject.Inject
import javax.inject.Named

class MoneyTypeConverter {
    @Inject
    @field:Named("AES")
    internal lateinit var encryptor: Encryptor

    /**
     *
     */
    @TypeConverter
    fun fromMoneyToDb(source: Money?): ByteArray? {
        if(source == null) {
            return null
        }

        if(!::encryptor.isInitialized) {
            App.injections.get<AppComponent>().inject(this)
        }

        val buffer = ByteBuffer.allocate(9)
            .putLong(source.totalCents)
            .put(source.currency.value)
            .array()

        return encryptor.encrypt(buffer)
    }

    /**
     *
     */
    @TypeConverter
    fun fromDbToMoney(source: ByteArray?): Money? {
        if(source == null) {
            return null
        }

        if(!::encryptor.isInitialized) {
            App.injections.get<AppComponent>().inject(this)
        }

        val buffer = ByteBuffer.wrap(encryptor.decrypt(source))

        return Money(
            buffer.getLong(0),
            Currency.from(buffer.get(8))
        )
    }
}