package com.syleiman.gingermoney.ui.common.binding_adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.extension.appResourcesProvider
import com.syleiman.gingermoney.ui.common.extension.getParentActivity

@BindingAdapter("selected_group")
fun setSelectedAccountGroup(view: TextView, value: MutableLiveData<AccountGroup>?) {
    view.getParentActivity()?.let { parentActivity ->
        value?.observe(parentActivity, Observer { value ->
            view.text = parentActivity.appResourcesProvider.getAccountGroupString(value)
        })
    }
}