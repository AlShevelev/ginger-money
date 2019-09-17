package com.syleiman.gingermoney.core.global_entities.money

import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import com.syleiman.gingermoney.ui.common.formatters.MoneySoftCentsFormatter

fun Money.toHardCentsString() = MoneyHardCentsFormatter().format(this)
fun Money.toSoftCentsString() = MoneySoftCentsFormatter().format(this)