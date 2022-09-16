package com.trimz.service.ui.confirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.petocare.R
import com.petocare.databinding.ActivityConfirmationBinding
import com.petocare.infra.base_class.BaseActivity
import com.petocare.ui.confirmation.ConfirmationViewModel
import com.petocare.ui.dashboard.DashboardActivity

class ConfirmationActivity : BaseActivity<ActivityConfirmationBinding, ConfirmationViewModel>() {

    override fun getLayout(): Int {
        return R.layout.activity_confirmation
    }

    override fun getViewModelClass(): Class<ConfirmationViewModel> {
        return ConfirmationViewModel::class.java
    }

    override fun onCreateView() {
        initView()
    }

    private fun initView() {
        binding!!.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

}