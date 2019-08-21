package com.syleiman.gingermoney.ui.common.mvvm.displaying_errors

/**
 * Restricted interface for error information for displaying
 */
interface DisplayingError

/**
 * Some general error
 */
class GeneralError : DisplayingError

/**
 * Some fixed text message
 */
data class TextError(
    val textMessage: String
) : DisplayingError

/**
 * Memo field is too long
 */
class MemoIsTooLong: DisplayingError

/**
 * Name field is empty
 */
class NameIsEmpty: DisplayingError

/**
 * Name field is too long
 */
class NameIsTooLong: DisplayingError