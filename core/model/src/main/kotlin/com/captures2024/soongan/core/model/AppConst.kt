package com.captures2024.soongan.core.model

data object AppConst {

    data object Network {
        const val AUTH_HEADER = "Authorization"
        const val AUTH_PREFIX = "Bearer"
        const val AGENT_HEADER = "User-Agent"
        const val OS = "ANDROID"

        const val ACCESS_TOKEN_ALLOW = "$AUTH_HEADER: true"
        const val REFRESH_TOKEN_ALLOW = "$AUTH_HEADER: false"
    }
}
