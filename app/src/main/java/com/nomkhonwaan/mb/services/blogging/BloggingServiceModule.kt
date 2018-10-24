package com.nomkhonwaan.mb.services.blogging

import com.apollographql.apollo.ApolloClient
import com.nomkhonwaan.mb.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BloggingServiceModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.builder()
                .serverUrl(BuildConfig.GRAPHQL_ENDPOINT)
                .build()
    }

}