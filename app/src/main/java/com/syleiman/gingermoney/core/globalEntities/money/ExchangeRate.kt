package com.syleiman.gingermoney.core.globalEntities.money

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
    val quoteRate: Double)