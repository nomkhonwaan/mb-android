package com.nomkhonwaan.mb.ui.main

import com.nomkhonwaan.mb.services.blogging.BloggingServiceModule
import com.nomkhonwaan.mb.ui.recentupdates.RecentUpdatesFragment
import com.nomkhonwaan.mb.ui.recentupdates.RecentUpdatesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [BloggingServiceModule::class, RecentUpdatesFragmentModule::class])
    abstract fun recentPostsFragmentInjector(): RecentUpdatesFragment

}
