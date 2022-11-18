package com.petocare.ui.splash

import android.content.Intent
import com.petocare.R
import com.petocare.databinding.ActivitySplashBinding
import com.petocare.infra.base_class.BaseActivity
import com.petocare.ui.login.LoginActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun onCreateView() {
        Timer().schedule(2000) {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }


}