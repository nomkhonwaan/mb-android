package com.nomkhonwaan.mb

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MbAndroidApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerMbAndroidApplicationComponent.create().inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

}
