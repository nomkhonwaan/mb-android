package com.nomkhonwaan.mb.services.blogging

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory
import dagger.Module
import dagger.Provides

@Module
class BloggingServiceModule {

    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.builder()
                // TODO: Find the proper to config a GraphQL endpoint instead of hard-coding
                .serverUrl("https://api.nomkhonwaan.com/graphql")
                .build()
    }

}