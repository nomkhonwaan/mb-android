package com.nomkhonwaan.mb

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MbApplicationModule::class])
interface MbApplicationComponent {

    fun inject(app: MbApplication)

}
