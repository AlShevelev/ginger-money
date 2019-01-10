package com.syleiman.gingermoney.ui.activities.setup.fragments.protectionMethod.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.navigation.NavigationHelperInterface
import kotlinx.android.synthetic.main.fragment_setup_protection_method.*
import javax.inject.Inject

/**
 * Fragment for setup protection method for the app
 */
class ProtectionMethodFragment : Fragment() {

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.injections.get<SetupActivityComponent>().inject(this)

        return inflater.inflate(R.layout.fragment_setup_protection_method, container, false)
    }

    /**
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finishButton.setOnClickListener {
            navigation.moveToNext(this)
        }
    }
}
