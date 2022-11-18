package com.petocare.ui.login

import android.util.Log
import com.petocare.R
import com.petocare.databinding.ActivityLoginBinding
import com.petocare.infra.base_class.BaseActivity
import com.petocare.ui.login.screen.LoginFragment
import es.dmoral.toasty.Toasty

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {

    var backState = false

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun onCreateView() {
        addFragment(LoginFragment(), LoginFragment::class.java.name)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.rootContainer)
        if (currentFragment != null && !currentFragment.tag.equals(LoginFragment::class.java.name)) {
            val tag = currentFragment.tag
            Log.e("Add tag", ">>>$tag")
            backState = false
            popFragmentFromBackStack()
        } else {
            if (!backState){
                backState = true
                Toasty.info(this,"Press again to exit", Toasty.LENGTH_SHORT, true).show()
            }else {
                super.onBackPressed()
                finish()
            }
        }
    }
}