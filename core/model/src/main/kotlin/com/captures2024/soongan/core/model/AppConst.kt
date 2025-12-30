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

        object Gallery {
            const val PAGE_SIZE = 50
            const val SCROLL_PICKER_VISIBLE_OPTION_COUNT = 9
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

    object InAppBrowser {
        const val PRIVACY_POLICY = "https://abyssinian-cherry-9fc.notion.site/71392fc225bf47b69e353739a74829db?pvs=4"
        const val TERMS = "https://abyssinian-cherry-9fc.notion.site/5724dc92a43c4e7e94fd5ccf8ab0608b"
        const val INQUIRY_FORM = "https://forms.gle/sPEVxtXh9Kt4mVm36"
    }

    object External {
        const val PLAY_STORE_PREFIX = "market://details?id="
    }
}
