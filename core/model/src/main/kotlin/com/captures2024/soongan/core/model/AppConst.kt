package com.captures2024.soongan.core.model

object AppConst {
    const val EMPTY_STRING = ""

    object Sign {

        object SignUp {
            const val MAX_NICKNAME_LENGTH = 10
            const val MAX_BIRTH_LENGTH = 4
        }
    }

    object Notification {
        const val PUSH_ACTION_NAME = "com.captures2024.soongan.push"
    }

    object Network {
        const val AUTH_HEADER = "Authorization"
        const val AUTH_PREFIX = "Bearer"
        const val AGENT_HEADER = "User-Agent"
        const val OS = "ANDROID"

        const val ACCESS_TOKEN_ALLOW = "$AUTH_HEADER: true"
        const val REFRESH_TOKEN_ALLOW = "$AUTH_HEADER: false"
    }

    object Gallery {
        const val PAGE_SIZE = 20
    }
}
