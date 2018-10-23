package com.nomkhonwaan.mb

import com.nomkhonwaan.mb.ui.main.MainActivity
import com.nomkhonwaan.mb.ui.main.MainActivityModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class])
abstract class MbAndroidApplicationModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

}