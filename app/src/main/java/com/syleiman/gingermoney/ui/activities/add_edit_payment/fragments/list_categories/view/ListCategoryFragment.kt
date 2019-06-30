package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.syleiman.gingermoney.R

class ListCategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText("ListCategoryFragment")
        }
    }
}
