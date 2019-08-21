package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_edit_category.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class EditCategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText("EditCategoryFragment")
        }
    }
}
