package com.petocare.infra.base_class

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.petocare.R
import java.lang.IllegalStateException

abstract class BaseActivity<Binding: ViewDataBinding, ViewModel: BaseViewModel>: AppCompatActivity() {

    protected var binding: Binding? = null
    protected lateinit var viewModel: ViewModel

    protected abstract fun getLayout(): Int
    protected abstract fun getViewModelClass(): Class<ViewModel>
    protected abstract fun onCreateView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding?.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        onCreateView()
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

    fun replaceFragment(fragment: Fragment, fragmentTag: String?) {
//        popFragmentFromBackStack()
//        addFragment(fragment, fragmentTag)
        currentFragment = fragment
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.rootContainer, fragment, fragmentTag)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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

    fun popFragmentFromBackStack() {
        try {
            fragmentManager!!.popBackStack()
        } catch (e: IllegalStateException){
            e.printStackTrace()
        }
    }

    fun tellFragments() {
        val fragments =
                supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is BaseFragment)
                f.onBackPressed()
        }
    }
}