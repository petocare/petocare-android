package com.petocare.ui.dashboard.autoship

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petocare.R

class AutoshipFragment : Fragment() {

    companion object {
        fun newInstance() = AutoshipFragment()
    }

    private lateinit var viewModel: AutoshipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.autoship_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AutoshipViewModel::class.java)
        // TODO: Use the ViewModel
    }

}