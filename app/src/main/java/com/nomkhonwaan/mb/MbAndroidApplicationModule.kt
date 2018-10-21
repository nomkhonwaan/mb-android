package com.nomkhonwaan.mb

import com.nomkhonwaan.mb.services.blogging.BloggingServiceModule
import com.nomkhonwaan.mb.ui.main.MainActivity
import com.nomkhonwaan.mb.ui.main.MainActivityModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class MbAndroidApplicationModule {

    @ContributesAndroidInjector(modules = [BloggingServiceModule::class, MainActivityModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

}