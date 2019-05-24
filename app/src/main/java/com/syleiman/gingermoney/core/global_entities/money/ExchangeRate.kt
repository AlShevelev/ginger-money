package com.syleiman.gingermoney.core.global_entities.money

/** Exchange rate for [Money] */
class ExchangeRate(

    /**
     * Source currency
     */
    val from: Currency,

    /**
     * Target currency
     */
    val to: Currency,

    /**
     * How many units of currency [from] you can buy for one unit of currency [to]
     */
    val quoteFactor: Double
)