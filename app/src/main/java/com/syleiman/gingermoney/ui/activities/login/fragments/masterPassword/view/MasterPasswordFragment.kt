package com.syleiman.gingermoney.ui.activities.login.fragments.masterPassword.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.syleiman.gingermoney.R

/**
 * Fragment for authentication via master-password.
 */
class MasterPasswordFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_master_password, container, false)
    }
}
