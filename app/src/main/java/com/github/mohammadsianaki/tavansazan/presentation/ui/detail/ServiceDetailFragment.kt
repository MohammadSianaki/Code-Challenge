package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mohammadsianaki.core.ui.RecyclerFragment
import com.github.mohammadsianaki.tavansazan.R
import com.github.mohammadsianaki.tavansazan.databinding.ServiceDetailFragmentBinding

class ServiceDetailFragment : RecyclerFragment<ServiceDetailFragmentBinding,,ServiceDetailViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.service_detail_fragment, container, false)
    }

}