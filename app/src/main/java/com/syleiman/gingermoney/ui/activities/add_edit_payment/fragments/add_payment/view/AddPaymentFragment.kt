package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.syleiman.gingermoney.R

class AddPaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText("AddPaymentFragment")
        }
    }
}
