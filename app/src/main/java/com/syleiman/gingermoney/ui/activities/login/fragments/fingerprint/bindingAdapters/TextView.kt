package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.bindingAdapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.dto.TextStyle
import com.syleiman.gingermoney.ui.common.extension.getParentActivity

/**
 *
 */
@BindingAdapter("textStyle")
fun setTextStyle(view: View, value: MutableLiveData<TextStyle>?) {
    view.getParentActivity()?.let { parentActivity ->
        val textView = view as TextView

        value?.observe(parentActivity, Observer { textStyleValue ->
            textView.setTextColor(textStyleValue.color)
            textView.typeface = textStyleValue.typeface
        })
    }
}
