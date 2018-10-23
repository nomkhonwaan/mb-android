package com.nomkhonwaan.mb.ui.main

import com.nomkhonwaan.mb.services.blogging.BloggingServiceModule
import com.nomkhonwaan.mb.ui.recentposts.RecentPostsFragment
import com.nomkhonwaan.mb.ui.recentposts.RecentPostsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [BloggingServiceModule::class, RecentPostsFragmentModule::class])
    abstract fun recentPostsFragmentInjector(): RecentPostsFragment

}
