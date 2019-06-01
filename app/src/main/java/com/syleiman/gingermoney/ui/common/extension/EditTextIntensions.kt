package com.syleiman.gingermoney.ui.common.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Simplifying text change event for EditText
 */
fun EditText.setOnTextChangeListener(textChangeCallback: (String) -> Unit): TextWatcher {
    val watcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/* do nothing */ }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { /* do nothing */ }

        override fun afterTextChanged(editable: Editable?) = textChangeCallback(editable.toString())
    }

    this.addTextChangedListener(watcher)

    return watcher
}

fun EditText.clearOnTextChangeListener(watcher: TextWatcher) {
    this.removeTextChangedListener(watcher)
}