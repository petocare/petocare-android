package com.petocare.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.petocare.R

class DashboardActivity : AppCompatActivity() {

    val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initMvvm()
    }

    private fun initView() {

    }

    private fun initMvvm() {
        viewModel.getUsers().observe(this, Observer {

        })
    }

}