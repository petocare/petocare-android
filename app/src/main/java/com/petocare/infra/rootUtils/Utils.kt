package com.petocare.infra.rootUtils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object Utils {


  //getting retrofit instance
  fun getRetrofit(): Retrofit {
    val client = Retrofit.Builder()
    client.baseUrl("base_url")
    client.addConverterFactory(ScalarsConverterFactory.create())
    client.addConverterFactory(GsonConverterFactory.create())
    client.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    return client.build()
  }

  fun hideSoftKeyboard(activity: Activity) {
    try {
      val inputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    } catch (e: Exception) {
      Log.e(Utils::class.java.name, e?.localizedMessage ?: "")
    }
  }

  fun isConnectedToInternet(context: Context): Boolean {
    val connectivity = context.getSystemService(
      Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    val info = connectivity.allNetworkInfo
    for (i in info.indices)
      if (info[i].state == NetworkInfo.State.CONNECTED) return true

    longToast(context, "No Internet Connection")
    return false
  }

  fun longToast(context: Context, msg: Any) {
    Toast.makeText(context, msg.toString(), Toast.LENGTH_LONG).show()
  }

}