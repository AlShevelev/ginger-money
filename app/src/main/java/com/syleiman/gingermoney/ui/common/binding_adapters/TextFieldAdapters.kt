package com.syleiman.gingermoney.ui.common.binding_adapters

import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.extension.appResourcesProvider
import com.syleiman.gingermoney.ui.common.extension.getParentActivity
import com.syleiman.gingermoney.ui.common.widgets.EditTextWithEmoji
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * For text field with account group
 */
@BindingAdapter("account_group")
fun setSelectedAccountGroup(view: TextView, valueToBind: MutableLiveData<AccountGroup>?) {
    view.getParentActivity()?.let { parentActivity ->
        valueToBind?.observe(parentActivity, Observer { observedValue ->
            view.text = parentActivity.appResourcesProvider.getAccountGroupString(observedValue)
        })
    }
}

/**
 * For [EditTextWithEmoji], binds text field value
 */
@BindingAdapter("emoji_text")
fun setTextForEditTextWithEmoji(view: EditTextWithEmoji, valueToBind: MutableLiveData<String>?) {
    valueToBind?.let { value ->
        view.getParentActivity()?.let { parentActivity ->
            value.observe(parentActivity, Observer { observedValue ->
                val currentText = view.text?.toString()

                if(currentText != observedValue) {
                    view.text = SpannableStringBuilder(observedValue)
                }
            })
        }

        view.setOnTextChangeListener { text ->
            if(value.value != text) {
                value.value = text
            }
        }
    }
}

/**
 * For [EditTextWithEmoji], binds text field maximum length
 */
@BindingAdapter("max_length")
fun setMaxLengthForEditTextWithEmoji(view: EditTextWithEmoji, valueToBind: MutableLiveData<Int>?) {
    valueToBind?.let { value ->
        view.getParentActivity()?.let { parentActivity ->
            value.observe(parentActivity, Observer { observedValue ->
                view.setTextMaxLength(observedValue)
            })
        }
    }
}

/**
 * For text field with date & time
 */
@BindingAdapter("date_time")
fun setDateAndTime(view: TextView, valueToBind: MutableLiveData<ZonedDateTime>?) =
    bindDateTime(view, valueToBind, R.string.dateTimeFormat)

/**
 * For text field with date only
 */
@BindingAdapter("date")
fun setDate(view: TextView, valueToBind: MutableLiveData<ZonedDateTime>?) = bindDateTime(view, valueToBind, R.string.dateFormat)

/**
 * For text field with time only
 */
@BindingAdapter("time")
fun setTime(view: TextView, valueToBind: MutableLiveData<ZonedDateTime>?) = bindDateTime(view, valueToBind, R.string.timeFormat)

private fun bindDateTime(view: TextView, valueToBind: MutableLiveData<ZonedDateTime>?, @StringRes formatResId: Int) {
    view.getParentActivity()?.let { parentActivity ->
        valueToBind?.observe(parentActivity, Observer { observedValue ->
            val formatter = DateTimeFormatter.ofPattern(parentActivity.appResourcesProvider.getString(formatResId))
            view.text = formatter.format(observedValue)
        })
    }
}