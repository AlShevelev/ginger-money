package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dependency_injection.ListCategoryFragmentComponent
import com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.list_categories.ListCategoriesHeaderLink
import javax.inject.Inject

class ListCategoryFragment : Fragment(), ListCategoryFragmentHeaderLink {
    @Inject
    lateinit var linkToHeader: ListCategoriesHeaderLink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.injections.get<ListCategoryFragmentComponent>().inject(this)
        linkToHeader.attachFragment(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        App.injections.release<ListCategoryFragmentComponent>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TextView(activity).apply {
            setText("ListCategoryFragment")
        }
    }

    override fun onAddButtonClick() {
        return
    }
}
