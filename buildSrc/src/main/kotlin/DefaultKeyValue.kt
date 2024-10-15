import org.gradle.api.Project
import java.io.FileNotFoundException
import java.util.Properties

object DefaultKeyValue {
    private const val DEFAULT_BASE_URL = "YOUR_BASE_URL"
    private const val DEFAULT_GOOGLE_API_KEY = "YOUR_API_KEY"
    private const val DEFAULT_GOOGLE_CLIENT_ID = "YOUR_CLIENT_ID"
    private const val DEFAULT_KAKAO_API_KEY = "YOUR_API_KEY"
    private const val DEFAULT_APPLE_ENDPOINT = "YOUR_ENDPOINT"
    private const val DEFAULT_APPLE_REDIRECT_URL = "YOUR_REDIRECT_URL"
    private const val DEFAULT_APPLE_RESPONSE_URL = "YOUR_RESPONSE_URL"

    @Throws(
        IllegalArgumentException::class,
        NullPointerException::class,
    )
    fun isAllowedBaseUrl(key: String?): String {
        if (key == null) {
            throw NullPointerException("base url is null. Please input your base url")
        }

        if (key == DEFAULT_BASE_URL) {
            throw IllegalArgumentException("base url is default. Please input your base url")
        }

        return key
    }

    @Throws(
        IllegalArgumentException::class,
        NullPointerException::class,
    )
    fun isAllowedGoogleApiKey(key: String?): String {
        if (key == null) {
            throw NullPointerException("google api key is null. Please input your google api key")
        }

        if (key == DEFAULT_GOOGLE_API_KEY) {
            throw IllegalArgumentException("google api key is default. Please input your google api key")
        }

        return key
    }

    @Throws(
        IllegalArgumentException::class,
        NullPointerException::class,
    )
    fun isAllowedGoogleClientId(key: String?): String {
        if (key == null) {
            throw NullPointerException("google client id is null. Please input your google client id")
        }

        if (key == DEFAULT_GOOGLE_CLIENT_ID) {
            throw IllegalArgumentException("google client id is default. Please input your google client id")
        }

        return key
    }

    @Throws(
        IllegalArgumentException::class,
        NullPointerException::class,
    )
    fun isAllowedKakaoApiKey(key: String?): String {
        if (key == null) {
            throw NullPointerException("kakao api key is null. Please input your kakao api key")
        }

        if (key == DEFAULT_KAKAO_API_KEY) {
            throw IllegalArgumentException("kakao api key is default. Please input your kakao api key")
        }

        return key
    }

    @Throws(
        IllegalArgumentException::class,
        NullPointerException::class,
    )
    fun isAllowedAppleEndpoint(key: String?): String {
        if (key == null) {
            throw NullPointerException("AppleEndpoint is null. Please input your kakao api key")
        }

        if (key == DEFAULT_APPLE_ENDPOINT) {
            throw IllegalArgumentException("AppleEndpoint is default. Please input your kakao api key")
        }

        return key
    }

    @Throws(
        IllegalArgumentException::class,
        NullPointerException::class,
    )
    fun isAllowedAppleRedirectUrl(key: String?): String {
        if (key == null) {
            throw NullPointerException("AppleRedirectUrl is null. Please input your kakao api key")
        }

        if (key == DEFAULT_APPLE_REDIRECT_URL) {
            throw IllegalArgumentException("AppleRedirectUrl is default. Please input your kakao api key")
        }

        return key
    }

    @Throws(
        IllegalArgumentException::class,
        NullPointerException::class,
    )
    fun isAllowedAppleResponseUrl(key: String?): String {
        if (key == null) {
            throw NullPointerException("AppleResponseUrl is null. Please input your kakao api key")
        }

        if (key == DEFAULT_APPLE_RESPONSE_URL) {
            throw IllegalArgumentException("AppleResponseUrl is default. Please input your kakao api key")
        }

        return key
    }
}

@Throws(FileNotFoundException::class)
fun Project.loadProperties(): Properties = Properties().apply {
    val file = rootProject.file("secrets.properties")
    when (file.exists()) {
        false -> throw FileNotFoundException("secrets.properties is not founded")
        true -> load(file.inputStream())
    }
}