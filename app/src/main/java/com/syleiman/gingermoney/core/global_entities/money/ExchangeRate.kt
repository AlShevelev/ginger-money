package com.syleiman.gingermoney.core.global_entities.money

/** Exchange rate for [Money] */
class ExchangeRate(
    /**
     * Source selecedCurrency
     */
    val from: Currency,

    /**
     * Target selecedCurrency
     */
    val to: Currency,

    /**
     * How many units of selecedCurrency [from] you can buy for one unit of selecedCurrency [to]
     */
    val quoteFactor: Double
)