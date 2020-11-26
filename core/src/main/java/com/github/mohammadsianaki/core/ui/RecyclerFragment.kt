package com.github.mohammadsianaki.core.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.mohammadsianaki.core.R
import com.github.mohammadsianaki.core.extensions.gone
import com.github.mohammadsianaki.core.extensions.observeInFragment
import com.github.mohammadsianaki.core.extensions.visible
import com.github.mohammadsianaki.core.ui.adapter.BaseRecyclerAdapter
import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.core.utils.ResourceState

abstract class RecyclerFragment<VB : ViewBinding , Data : RecyclerData, VM : RecyclerViewModel<Data>> :
    BaseFragment<VB, VM>() {
    private var _recyclerView: RecyclerView? = null
    protected val recyclerView
        get() = _recyclerView!!

    private var _progressBar: ProgressBar? = null
    protected val progressBar
        get() = _progressBar!!

    private var _emptyView: ViewGroup? = null
    protected val emptyView
        get() = _emptyView!!

    private var _errorView: ViewGroup? = null
    protected val errorView
        get() = _errorView!!

    protected abstract val recyclerAdapter: BaseRecyclerAdapter<Data>
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        observeInFragment(viewModel.liveData, ::renderViewState)
    }

    override fun initUI(rootView: View) {
        _emptyView = viewBinding.root.findViewById(R.id.emptyView)
        _progressBar = viewBinding.root.findViewById(R.id.loadingView)
        _recyclerView = viewBinding.root.findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = recyclerAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onDestroyView() {
        _recyclerView?.adapter = null
        _recyclerView = null
        _progressBar = null
        _emptyView = null
        super.onDestroyView()
    }

    protected fun showRecyclerView() = recyclerView.visible()
    protected fun hideRecyclerView() = recyclerView.gone()
    protected open fun renderViewState(resource: Resource<List<Data>>?) {
        when (resource!!.resourceState) {
            ResourceState.Loading -> {
                handleLoadingState()
            }
            ResourceState.Success -> {
                handleSuccessState(resource)
            }
            ResourceState.Error -> {
                handleFailureState(resource)
            }
        }
    }

    protected open fun handleLoadingState() {
        showLoading()
        hideErrorView()
        hideEmptyView()
        hideRecyclerView()
    }
    protected open fun handleFailureState(resource: Resource<*>?) {
        hideLoading()
        hideEmptyView()
        hideRecyclerView()
        showErrorView()
        showErrorMessage(resource)
    }

    protected open fun handleSuccessState(resource: Resource<List<Data>>?) {
        check(resource!!.resourceState is ResourceState.Success)
        resource.data?.let { items ->
            hideLoading()
            hideErrorView()
            if (items.isNotEmpty()) {
                hideEmptyView()
                showRecyclerView()
                recyclerAdapter.items = items.toMutableList()
            } else {
                hideRecyclerView()
                showEmptyView()
            }
        }
    }

    protected  fun showEmptyView() = emptyView.visible()
    protected fun hideEmptyView() = emptyView.gone()
    protected fun showLoading() = progressBar.visible()
    protected fun hideLoading() = progressBar.gone()
    protected  fun showErrorView() = errorView.visible()
    protected fun hideErrorView() = errorView.gone()
    protected abstract fun loadData()
}
