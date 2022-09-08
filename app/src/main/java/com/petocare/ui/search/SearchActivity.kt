package com.petocare.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petocare.R
import com.petocare.adapter.SearchFilterAdapter
import com.petocare.adapter.SearchProductListAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    lateinit var searchFilterAdapter: SearchFilterAdapter
    lateinit var searchProductListAdapter: SearchProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    private fun initView() {
        initializeRecycler()
    }

    fun initializeRecycler(){
        searchFilterAdapter = SearchFilterAdapter(this, arrayListOf())
        searchProductListAdapter = SearchProductListAdapter(this, arrayListOf())
        filter_recycler.adapter = searchFilterAdapter
        product_recycler.adapter = searchProductListAdapter
    }
}