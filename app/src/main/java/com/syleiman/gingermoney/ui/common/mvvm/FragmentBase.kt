package com.syleiman.gingermoney.ui.common.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import com.syleiman.gingermoney.ui.common.displayingErrors.GeneralError
import com.syleiman.gingermoney.ui.common.uiUtils.UIUtilsInterface
import com.syleiman.gingermoney.ui.common.viewCommands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import javax.inject.Inject

/**
 * Base class for all fragments
 */
abstract class FragmentBase<TB: ViewDataBinding, TM: ModelBaseInterface, TVM: ViewModelBase<TM>> : Fragment() {

    private lateinit var binding: TB

    private lateinit var _viewModel: TVM
    protected val viewModel: TVM
    get() = _viewModel

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProviderInterface

    @Inject
    internal lateinit var uiUtils: UIUtilsInterface

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewModel = ViewModelProviders.of(this).get(provideViewModelType())

        _viewModel.command.observe({this.lifecycle}) {
            if(!processViewCommandGeneral(it)) {
                processViewCommand(it)
            }
        }
    }

    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inject()

        binding = DataBindingUtil.inflate(inflater, provideLayout(), container, false)
        binding.setLifecycleOwner(this)

        linkViewModel(binding, _viewModel)
        return binding.root
    }

    /**
     *
     */
    abstract fun provideViewModelType(): Class<TVM>

    /**
     *
     */
    @LayoutRes
    abstract fun provideLayout(): Int

    /**
     *
     */
    abstract fun inject()

    /**
     *
     */
    abstract fun linkViewModel(binding: TB, viewModel: TVM)

    /**
     *
     */
    abstract fun processViewCommand(command: ViewCommand)

    /**
     * Process input command
     * @return true if the command has been processed
     */
    private fun processViewCommandGeneral(command: ViewCommand): Boolean {
        when(command) {
            is ShowErrorCommand -> {
                when(command.error) {
                    is GeneralError -> {
                        uiUtils.showError(requireContext(), resourcesProvider.getString(R.string.commonGeneralError))
                        return true
                    }
                }
            }
        }
        return false
    }
}