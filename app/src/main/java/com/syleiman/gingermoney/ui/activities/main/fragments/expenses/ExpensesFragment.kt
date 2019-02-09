package com.syleiman.gingermoney.ui.activities.main.fragments.expenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.syleiman.gingermoney.R

/**
 * Expenses (Costs) page
 */
class ExpensesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_expenses, container, false)
    }
}
