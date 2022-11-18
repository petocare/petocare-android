package com.petocare.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petocare.R
import com.petocare.interfaces.OnBoardingListener
import com.petocare.ui.onBoarding.OnBoardingActivity
import java.util.*

class OnBoardingViewPagerAdapter(
    val list: ArrayList<String>, val activity: OnBoardingActivity, val listener: OnBoardingListener
) : RecyclerView.Adapter<OnBoardingViewPagerAdapter.PagerViewHolder>() {

  lateinit var context: Context

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
    val item = View.inflate(parent.context, R.layout.item_onboarding, null)
    val lp = ViewGroup.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.MATCH_PARENT
    )
    item.layoutParams = lp
    context = item.context
    return PagerViewHolder(item)
  }

  override fun getItemCount(): Int {
    return 5//list.size
  }

  override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
//    Glide.with(context).load(list.get(position).image?.url ?: "")
//      .into(holder.primaryImage)
//    holder.itemView.setOnClickListener {
//      dashboardListener.onPromoBannerClicked(list.get(position))
//    }
//    holder.title.setText(list.get(position).title)
//        checkBannerDetails(position)
  }


  class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.title)
    val desc = itemView.findViewById<TextView>(R.id.desc)
  }


}