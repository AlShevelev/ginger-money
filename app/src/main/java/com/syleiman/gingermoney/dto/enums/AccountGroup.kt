package com.syleiman.gingermoney.dto.enums

/**
 *
 */
enum class AccountGroup(val value: Byte) {
    CASH(0),
    CARDS(1),
    CREDIT_CARDS(2),
    DEBIT_CARDS(3),
    ACCOUNTS(4),
    DEPOSITS(5),
    SAVINGS(6),
    INVESTMENTS(7),
    SHARES(8),
    BONDS(9),
    OTHER(10);

    companion object Create {
        fun from(sourceValue: Byte): AccountGroup = values().first { it.value == sourceValue }
    }
}