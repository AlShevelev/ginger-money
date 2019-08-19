package com.syleiman.gingermoney.ui.activities.add_edit_payment.headers.list_categories

import android.view.View
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view.ListCategoryFragmentHeaderLink
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderLinkBaseImpl
import javax.inject.Inject

class ListCategoriesHeaderLinkImpl
@Inject
constructor(): HeaderLinkBaseImpl<ListCategoryFragmentHeaderLink, ListCategoriesHeaderFragmentLink>(), ListCategoriesHeaderLink {

    override fun attachFragment(fragment: ListCategoryFragmentHeaderLink) {
        super.attachFragment(fragment)

        header?.also {
            it.setAddButtonClickListener(View.OnClickListener { fragment.onAddButtonClick() })
        }
    }

    override fun attachHeader(header: ListCategoriesHeaderFragmentLink) {
        super.attachHeader(header)

        fragment?.also { fragment ->
            header.setAddButtonClickListener(View.OnClickListener { fragment.onAddButtonClick() })
        }

    }

    override fun detachFragment() {
        super.detachFragment()

        header?.also {
            it.setAddButtonClickListener(null)
        }
    }

    override fun detachHeader() {
        header?.also {
            it.setAddButtonClickListener(null)
        }

        super.detachHeader()
    }
}