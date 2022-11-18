package com.petocare.ui.onBoarding

import android.content.Intent
import android.os.CountDownTimer
import com.petocare.R
import com.petocare.adapter.OnBoardingViewPagerAdapter
import com.petocare.databinding.ActivityOnBoardingBinding
import com.petocare.infra.base_class.BaseActivity
import com.petocare.infra.rootUtils.HorizontalMarginItemDecoration
import com.petocare.interfaces.OnBoardingListener
import com.petocare.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding, OnBoardingViewModel>(), OnBoardingListener {

    lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter

    override fun getLayout(): Int {
        return R.layout.activity_on_boarding
    }

    override fun getViewModelClass(): Class<OnBoardingViewModel> {
        return OnBoardingViewModel::class.java
    }

    override fun onCreateView() {
        initView()
        initMvvM()
    }

    private fun initView() {
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(arrayListOf(), this, this)
        initializeOnBoardingViewPager()
        continue_btn.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }

    private fun initMvvM() {

    }

    private fun initializeOnBoardingViewPager() {
        onboarding_viewpager.adapter = onBoardingViewPagerAdapter
        onboarding_viewpager.offscreenPageLimit = 5
        onboarding_indicator.setViewPager2(onboarding_viewpager)
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        onboarding_viewpager.addItemDecoration(itemDecoration)
        timerSwap()
    }

    fun timerSwap(){
        var currentposition = 0
        val timer = object: CountDownTimer(10000, 2000) {
            override fun onTick(millisUntilFinished: Long) {
                onboarding_viewpager.currentItem = currentposition
                currentposition += 1
            }

            override fun onFinish() {
                onboarding_viewpager.currentItem = 0
                timerSwap()
            }
        }
        timer.start()
    }

}