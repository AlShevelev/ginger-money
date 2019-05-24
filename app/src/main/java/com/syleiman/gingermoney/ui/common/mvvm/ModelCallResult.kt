package com.syleiman.gingermoney.ui.common.mvvm

import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError

data class ModelCallResult<T> (
    val error: DisplayingError?,
    val value: T?
)