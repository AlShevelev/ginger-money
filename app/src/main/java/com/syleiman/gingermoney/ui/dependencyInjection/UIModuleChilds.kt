package com.syleiman.gingermoney.ui.dependencyInjection

import com.syleiman.gingermoney.ui.activities.root.dependencyInjection.RootActivityComponent
import com.syleiman.gingermoney.ui.activities.setup.dependencyInjection.SetupActivityComponent
import dagger.Module

/**
 *
 */
@Module(subcomponents = [
    RootActivityComponent::class,
    SetupActivityComponent::class
])
class UIModuleChilds