package com.captures2024.soongan.core.model

object AppConst {
    const val EMPTY_STRING = ""

    object Sign {

        object SignUp {
            const val MAX_NICKNAME_LENGTH = 10
            const val MAX_BIRTH_LENGTH = 4
        }
    }

    object Main {

        object Home {
            const val MAX_REGISTER_POST_COUNT: Int = 3
            const val MAX_INPUT_LENGTH: Int = 15
        }

        object Profile {
            const val MAX_NICKNAME_LENGTH = Sign.SignUp.MAX_NICKNAME_LENGTH
            const val MAX_INTRODUCTION_LENGTH = 20
        }

        object Post {
            const val REPORT_REASON_MAX_LENGTH = 200

            const val IMAGE_DEFAULT_DURATION: Long = 1L * 1L * 1000L
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
}
