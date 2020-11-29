package com.github.mohammadsianaki.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.viewbinding.ViewBinding
import com.github.mohammadsianaki.core.extensions.gone
import com.github.mohammadsianaki.core.extensions.observerNavigationCommands
import com.github.mohammadsianaki.core.extensions.showMessage
import com.github.mohammadsianaki.core.networkconnection.NetworkStateListener
import com.github.mohammadsianaki.core.networkconnection.manager.ReceiverManager
import com.github.mohammadsianaki.core.utils.Resource

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel<Params>, Params : NavArgs> :
    Fragment(),
    NetworkStateListener {

    private var _viewBinding: VB? = null
    protected val viewBinding: VB
        get() = _viewBinding!!

    protected abstract val viewModel: VM
    protected abstract val navArgs: Params

    private var toolbar: Toolbar? = null
    protected open val showToolbar: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _viewBinding = createViewBinding(container)
        return _viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
        observerNavigationCommands(viewModel.navigationCommands)
    }

    override fun onStart() {
        super.onStart()
        ReceiverManager.registerNetworkChangeReceiver(requireActivity(), this)
    }

    override fun onStop() {
        super.onStop()
        ReceiverManager.unregisterNetworkChangeReceiver(requireActivity(), this)
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

    protected open fun showErrorMessage(resource: Resource<*>?) {
        resource?.failure?.message?.let { msg ->
            showMessage(msg)
        }
    }

    protected abstract fun createViewBinding(container: ViewGroup?): VB?
    protected open fun initUI(rootView: View) {
        if (showToolbar) {
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        } else {
            toolbar?.gone()
        }
    }
}
