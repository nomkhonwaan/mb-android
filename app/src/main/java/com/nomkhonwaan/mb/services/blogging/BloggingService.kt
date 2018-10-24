package com.nomkhonwaan.mb.services.blogging

import com.apollographql.apollo.ApolloClient
import javax.inject.Inject

class BloggingService {

    @Inject
    lateinit var apolloClient: ApolloClient

}