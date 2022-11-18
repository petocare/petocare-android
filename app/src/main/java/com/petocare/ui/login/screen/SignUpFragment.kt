package com.petocare.ui.login.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petocare.R
import com.petocare.base.BaseFragment
import com.petocare.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : BaseFragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initMvvm()
    }

    private fun initView() {
        continue_btn.setOnClickListener {
            (activity as LoginActivity).addOrReplaceFragment(VerifyFragment(), VerifyFragment::class.java.name)
        }
    }

    private fun initMvvm() {

    }

}