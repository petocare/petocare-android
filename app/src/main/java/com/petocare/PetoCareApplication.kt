package com.petocare

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.petocare.infra.base_class.FTActivityLifecycleCallbacks

class PetoCareApplication: MultiDexApplication() {

    val mFTActivityLifecycleCallbacks = FTActivityLifecycleCallbacks()

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(mFTActivityLifecycleCallbacks)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        initModule(this)
    }

    companion object {
        lateinit var instance: MultiDexApplication

        @JvmStatic
        fun initModule(application: MultiDexApplication) {
            instance = application
            apiInitialize()
        }

        @JvmStatic
        fun apiInitialize() {
//            AndroidAppApiClient.shared.init("https://raw.githubusercontent.com/ravitejaavv/")
        }

        fun currentActivity(): AppCompatActivity? {
            return (instance as? PetoCareApplication)?.mFTActivityLifecycleCallbacks?.currentActivity as? AppCompatActivity
        }
        fun isInitialised() = Companion::instance.isInitialized
    }

    override fun attachBaseContext(base: Context) {
        MultiDex.install(this)
        super.attachBaseContext(base)
    }
}