package com.syleiman.gingermoney.ui.common.bindingAdapters

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.syleiman.gingermoney.core.globalEntities.money.Currency
import com.syleiman.gingermoney.ui.common.extension.getParentActivity

@BindingAdapter("selectedCurrency")
fun setSelectedCurrency(view: View, value: MutableLiveData<Currency>?) {
    view.getParentActivity()?.let { parentActivity ->
        value?.observe(parentActivity, Observer { value ->
            view.findViewWithTag<RadioButton>(value.toString())
                ?.also {
                    if(!it.isChecked) {
                        it.isChecked = true
                    }
                }
            }
        )

        (view as RadioGroup).setOnCheckedChangeListener { radioGroup, checkedId ->
            val currency = Currency.from(radioGroup.findViewById<RadioButton>(checkedId).tag as String)
            if(value != null && value.value != currency) {
                value.value = currency
            }
        }
    }
}
