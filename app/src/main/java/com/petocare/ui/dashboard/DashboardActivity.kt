package com.petocare.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.petocare.R
import com.petocare.base.BaseFragment
import com.petocare.ui.dashboard.autoship.AutoshipFragment
import com.petocare.ui.dashboard.home.HomeFragment
import com.petocare.ui.dashboard.offers.OffersFragment
import com.petocare.ui.dashboard.profile.ProfileFragment
import com.petocare.ui.dashboard.veterinary.VeterinaryFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.lang.IllegalStateException

class DashboardActivity : AppCompatActivity() {

    val viewModel: DashboardViewModel by viewModels()
    var backState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
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

    private var currentFragment: Fragment? = null
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null

    fun addFragment(fragment: Fragment, fragmentTag: String?) {
        currentFragment = fragment
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.add(R.id.rootContainer, fragment, fragmentTag)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction!!.addToBackStack(fragmentTag)
        fragmentTransaction!!.commit()
    }

    fun addOrReplaceFragment(fragment: Fragment, fragmentTag: String?) {
        currentFragment = fragment
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.rootContainer, fragment, fragmentTag)
//        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction!!.commit()
    }

    fun replaceFragment(fragment: Fragment, fragmentTag: String?) {
        popFragmentFromBackStack()
        addFragment(fragment, fragmentTag)
    }

    private fun tellFragments() {
        val fragments =
            supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is BaseFragment)
                f.onBackPressed()
        }
    }

    fun popFragmentFromBackStack() {
        try {
            fragmentManager!!.popBackStack()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            //ignore
        }
    }

}