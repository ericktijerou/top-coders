package com.ericktijerou.topcoders.data.network

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import com.ericktijerou.topcoders.BuildConfig
import java.util.concurrent.Executor

class AuthInterceptor : ApolloInterceptor {
    override fun interceptAsync(
        request: ApolloInterceptor.InterceptorRequest,
        chain: ApolloInterceptorChain,
        dispatcher: Executor,
        callBack: ApolloInterceptor.CallBack
    ) {
        chain.proceedAsync(
            request.toBuilder().requestHeaders(
                request.requestHeaders.toBuilder().addHeader(
                    HEADER_AUTHORIZATION,
                    "$HEADER_AUTHORIZATION_BEARER ${BuildConfig.GITHUB_OAUTH_TOKEN}"
                ).build()
            ).build(), dispatcher, callBack
        )
    }

    override fun dispose() {}

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_AUTHORIZATION_BEARER = "Bearer"
    }
}