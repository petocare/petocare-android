package com.petocare.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.petocare.R
import com.petocare.interfaces.HomeListener
import com.petocare.ui.dashboard.DashboardActivity
import java.util.*

class BannerViewPagerAdapter(
    val list: ArrayList<String>, val activity: DashboardActivity, val dashboardListener: HomeListener
) : RecyclerView.Adapter<BannerViewPagerAdapter.PagerViewHolder>() {

  lateinit var context: Context

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
    val item = View.inflate(parent.context, R.layout.item_promo_banner, null)
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
    val primaryImage = itemView.findViewById<ImageView>(R.id.primary_image)
  }


}