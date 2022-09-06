package com.petocare.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petocare.R

class TopCategoryAdapter(
    val activity: Activity,
    var list: ArrayList<String>
) : RecyclerView.Adapter<TopCategoryAdapter.petViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): petViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(
            R.layout.pets_category, parent, false
        )
        context = itemView.context
        return petViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 6//upgradeList.size
    }

    override fun onBindViewHolder(holder: petViewHolder, position: Int) {

    }

    fun addupdates(updatelist: ArrayList<String>) {
        list = updatelist
    }

    class petViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}