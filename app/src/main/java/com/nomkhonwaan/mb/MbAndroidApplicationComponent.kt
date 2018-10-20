package com.nomkhonwaan.mb

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MbAndroidApplicationModule::class])
interface MbAndroidApplicationComponent {

    fun inject(app: MbAndroidApplication)

}
