package com.nomkhonwaan.mb.services.blogging

import com.apollographql.apollo.ApolloClient
import com.nomkhonwaan.mb.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class BloggingServiceModule {

    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.builder()
                .serverUrl(BuildConfig.GRAPHQL_ENDPOINT)
                .build()
    }

    @Provides
    fun provideBloggingService(): BloggingService {
        return BloggingService(provideApolloClient())
    }

}