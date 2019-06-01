package com.syleiman.gingermoney.ui.common.binding_adapters

import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.extension.appResourcesProvider
import com.syleiman.gingermoney.ui.common.extension.getParentActivity
import com.syleiman.gingermoney.ui.common.widgets.EditTextWithEmoji

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