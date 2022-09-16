package com.petocare.infra.rootUtils

import android.content.Context
import android.net.ConnectivityManager
import com.petocare.PetoCareApplication


object NetworkUtils {

  fun isNetworkConnected(): Boolean {
    val connectivityManager =
      PetoCareApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo?.isConnected ?: false

  }

}
