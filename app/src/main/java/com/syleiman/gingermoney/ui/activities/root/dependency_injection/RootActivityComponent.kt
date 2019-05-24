package com.syleiman.gingermoney.ui.activities.root.dependency_injection

import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.ui.activities.root.RootActivity
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface RootActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): RootActivityComponent
    }

    fun inject(activity : RootActivity)
}