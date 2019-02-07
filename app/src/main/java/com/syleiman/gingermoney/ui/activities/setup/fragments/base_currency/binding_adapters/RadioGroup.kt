package com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.binding_adapters

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.common.extension.getParentActivity

@BindingAdapter("selected_currency")
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