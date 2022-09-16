package com.petocare.ui.dashboard

import android.util.Log
import com.petocare.R
import com.petocare.databinding.ActivityDashboardBinding
import com.petocare.infra.base_class.BaseActivity
import com.petocare.ui.dashboard.autoship.AutoshipFragment
import com.petocare.ui.dashboard.home.HomeFragment
import com.petocare.ui.dashboard.offers.OffersFragment
import com.petocare.ui.dashboard.profile.ProfileFragment
import com.petocare.ui.dashboard.veterinary.VeterinaryFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>() {

    var backState = false


    override fun getLayout(): Int {
        return R.layout.activity_dashboard
    }

    override fun getViewModelClass(): Class<DashboardViewModel> {
        return DashboardViewModel::class.java
    }

    override fun onCreateView() {
        addFragment(HomeFragment(), HomeFragment::class.java.name)
        setUpNavigation()
        initView()
        initMvvm()
    }

    private fun initView() {
        addFragment(HomeFragment.newInstance(), HomeFragment::class.java.name)

        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment =
                supportFragmentManager.findFragmentById(R.id.rootContainer)
            if (currentFragment != null) {
                val tag = currentFragment.tag
                Log.e("Add tag", ">>>$tag")
                tellFragments()
                backState = false
            }
        }
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.rootContainer)
        if (currentFragment != null && !currentFragment.tag.equals(HomeFragment::class.java.name)) {
            val tag = currentFragment.tag
            Log.e("Add tag", ">>>$tag")
            backState = false
            bottom_navigation_view.selectedItemId = R.id.action_home
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

    private fun initMvvm() {

    }

    private fun setUpNavigation() {
        bottom_navigation_view.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    addOrReplaceFragment(HomeFragment(), HomeFragment::class.java.name)
                }
                R.id.action_veterinary -> {
                    addOrReplaceFragment(VeterinaryFragment(), VeterinaryFragment::class.java.name)
                }
                R.id.action_offers -> {
                    addOrReplaceFragment(OffersFragment(), OffersFragment::class.java.name)
                }
                R.id.action_autoship -> {
                    addOrReplaceFragment(AutoshipFragment(), AutoshipFragment::class.java.name)
                }
                R.id.action_profile -> {
                    addOrReplaceFragment(ProfileFragment(), ProfileFragment::class.java.name)
                }
            }
            true
        }
    }

}