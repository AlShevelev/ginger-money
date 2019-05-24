package com.syleiman.gingermoney.core.global_entities.money

import java.lang.RuntimeException

class IncorrectMoneyOperationException(message: String): RuntimeException(message)