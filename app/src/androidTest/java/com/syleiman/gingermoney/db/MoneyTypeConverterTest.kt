package com.syleiman.gingermoney.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.storages.db.type_converters.MoneyTypeConverter
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class MoneyTypeConverterTest {
    /**
     *
     */
    @Test
    fun fromMoneyToDbNull() {
        // Arrange
        val sourceValue: Money? = null
        val converter = MoneyTypeConverter()

        // Act
        val resultValue = converter.fromMoneyToDb(sourceValue)

        // Assert
        assertNull(resultValue)
    }

    /**
     *
     */
    @Test
    fun fromDbToMoneyNull() {
        // Arrange
        val sourceValue: ByteArray? = null
        val converter = MoneyTypeConverter()

        // Act
        val resultValue = converter.fromDbToMoney(sourceValue)

        // Assert
        assertNull(resultValue)
    }

    /**
     *
     */
    @Test
    fun toDbFromDb() {
        // Arrange
        val sourceValue = Money(155, Currency.USD)
        val converter = MoneyTypeConverter()

        // Act
        val restoredValue = converter.fromDbToMoney(converter.fromMoneyToDb(sourceValue))

        // Assert
        assertNotNull(restoredValue)
        assertEquals(sourceValue, restoredValue)
    }
}