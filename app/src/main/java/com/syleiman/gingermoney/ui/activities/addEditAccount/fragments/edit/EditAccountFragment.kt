package com.syleiman.gingermoney.ui.activities.addEditAccount.fragments.edit


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.syleiman.gingermoney.R

/**
 * A simple [Fragment] subclass.
 *
 */
class EditAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_edit_account_edit, container, false)
    }


}
