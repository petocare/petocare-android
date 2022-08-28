package com.petocare.infra.RootUtils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
class SharedPrefs(activity: Activity) {

  private val INITIAL_LOAD = "Initial_Load_App"

  private var editor: SharedPreferences.Editor? = null

  var pref: SharedPreferences? = null

  init {
    pref = activity.getSharedPreferences("PetoCare", 0)
    editor = pref!!.edit()
  }

  //initial load market place verification
  fun storeInitialLoadMarketPlace(state: Boolean) {
    editor!!.putBoolean(INITIAL_LOAD, state).apply()
  }

  fun getInitialLoadMarketPlace(): Boolean {
    return pref!!.getBoolean(INITIAL_LOAD, true)
  }

}