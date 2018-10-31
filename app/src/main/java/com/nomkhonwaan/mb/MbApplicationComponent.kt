package com.nomkhonwaan.mb

import dagger.Component

@Component(modules = [MbApplicationModule::class])
interface MbApplicationComponent {

    fun inject(app: MbApplication)

}
