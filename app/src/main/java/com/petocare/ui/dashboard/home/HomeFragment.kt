package com.petocare.ui.dashboard.home

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petocare.R
import com.petocare.adapter.BannerViewPagerAdapter
import com.petocare.adapter.PetCategoryAdapter
import com.petocare.adapter.TopCategoryAdapter
import com.petocare.base.BaseFragment
import com.petocare.infra.RootUtils.HorizontalMarginItemDecoration
import com.petocare.interfaces.HomeListener
import com.petocare.ui.dashboard.DashboardActivity
import com.petocare.ui.search.SearchActivity
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment(), HomeListener {

    lateinit var bannerViewPagerAdapter: BannerViewPagerAdapter
    lateinit var petCategoryAdapter: PetCategoryAdapter
    lateinit var topCategoryAdapter: TopCategoryAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        initView()
        initMvvm()
    }

    private fun initView() {
        bannerViewPagerAdapter = BannerViewPagerAdapter(ArrayList(), activity as DashboardActivity, this)
        petCategoryAdapter = PetCategoryAdapter(activity as DashboardActivity, arrayListOf())
        topCategoryAdapter = TopCategoryAdapter(activity as DashboardActivity, arrayListOf())
        initializeBannerViewPager()
        initializeRecycler()

        search_btn.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initMvvm() {
//        viewModel.getUsers().observe(this, Observer {
//
//        })
    }

    private fun initializeBannerViewPager() {
        banner_layout.visibility = View.VISIBLE
        banner_viewpager.adapter = bannerViewPagerAdapter
        banner_viewpager.offscreenPageLimit = 4
        banner_indicator.setViewPager2(banner_viewpager)
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        banner_viewpager.addItemDecoration(itemDecoration)

    }

    private fun initializeRecycler() {
        pet_category_recycler.adapter = petCategoryAdapter
        top_dog_recycler.adapter = topCategoryAdapter
        top_cat_recycler.adapter = topCategoryAdapter
    }

    override fun onPromoBannerClicked(value: String) {

    }

}