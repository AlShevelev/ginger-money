package com.syleiman.gingermoney.ui.common.binding_adapters

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.extension.getParentActivity

@BindingAdapter("selected_currency")
fun setSelectedCurrency(view: View, value: MutableLiveData<Currency>?) =
    bind(view, value) { radioGroup, checkedId ->
        Currency.from(radioGroup.findViewById<RadioButton>(checkedId).tag as String)
    }


@BindingAdapter("selected_protection_method")
fun setAppProtectionMethod(view: View, value: MutableLiveData<AppProtectionMethod>?) =
    bind(view, value) { radioGroup, checkedId ->
        AppProtectionMethod.from(radioGroup.findViewById<RadioButton>(checkedId).tag as String)
    }

private fun <T>bind(view: View, value: MutableLiveData<T>?, getValue: (RadioGroup, Int) -> T) {
    view.getParentActivity()?.let { parentActivity ->
        value?.observe(parentActivity, Observer { value ->
            view.findViewWithTag<RadioButton>(value.toString())
                ?.also {
                    if(!it.isChecked) {
                        it.isChecked = true
                    }
                }
            })

        (view as RadioGroup).setOnCheckedChangeListener { radioGroup, checkedId ->
            val appProtection = getValue(radioGroup, checkedId)
            if(value != null && value.value != appProtection) {
                value.value = appProtection
            }
        }
    }
}