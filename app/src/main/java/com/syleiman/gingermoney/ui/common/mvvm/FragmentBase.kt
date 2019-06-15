package com.syleiman.gingermoney.ui.common.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.ui_utils.UIUtilsInterface
import com.syleiman.gingermoney.ui.common.view_commands.ShowErrorCommand
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import javax.inject.Inject

/**
 * Base class for all fragments
 */
abstract class FragmentBase<TB: ViewDataBinding, TM: ModelBaseInterface, TVM: ViewModelBase<TM>> : Fragment() {

    private lateinit var binding: TB

    private lateinit var _viewModel: TVM
    protected val viewModel: TVM
    get() = _viewModel

    protected var activeDialog: AlertDialog? = null

    @Inject
    internal lateinit var resourcesProvider: AppResourcesProviderInterface

    @Inject
    internal lateinit var uiUtils: UIUtilsInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        _viewModel = ViewModelProviders.of(this).get(provideViewModelType())

        _viewModel.command.observe({this.lifecycle}) {
            if(!processViewCommandGeneral(it)) {
                processViewCommand(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, provideLayout(), container, false)
        binding.lifecycleOwner = this

        linkViewModel(binding, _viewModel)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Close a dialog to avoid leak of view
        activeDialog?.takeIf { it.isShowing }?.dismiss()
        activeDialog = null
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseInjection()
    }

    abstract fun provideViewModelType(): Class<TVM>

    @LayoutRes
    protected abstract fun provideLayout(): Int

    protected abstract fun inject()

    protected open fun releaseInjection() {}

    protected abstract fun linkViewModel(binding: TB, viewModel: TVM)

    protected open fun processViewCommand(command: ViewCommand) {}

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