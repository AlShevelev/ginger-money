package com.syleiman.gingermoney.ui.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.main.dependency_injection.MainActivityComponent
import com.syleiman.gingermoney.ui.activities.main.navigation.NavigationHelperInterface
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 *
 */
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigation: NavigationHelperInterface

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)

        App.injections.get<MainActivityComponent>().inject(this)

        navigation.setOnDestinationChangedListener(this) { destination ->
            title = destination.label
        }

        navigation.linkToBottomNavigation(this, mainNavPanel)
    }

    /**
     *
     */
    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) {
            App.injections.release<MainActivityComponent>()
        }
    }
}
