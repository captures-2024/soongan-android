package com.captures2024.soongan.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val dataStore: DataStore<Preferences>,
) : TokenDataSource {

    init {
        analyticsHelper.d { "TokenDataSource::init" }
    }

    override suspend fun setAccessToken(accessToken: String) {
        analyticsHelper.d { "setAccessToken - accessToken: $accessToken" }
        dataStore.edit { preferences -> preferences[KEY_ACCESS_TOKEN] = accessToken }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        analyticsHelper.d { "setRefreshToken - refreshToken: $refreshToken" }
        dataStore.edit { preferences -> preferences[KEY_REFRESH_TOKEN] = refreshToken }
    }

    override suspend fun setUUID(uuid: Long) {
        analyticsHelper.d { "setUUID - uuid: $uuid" }
        dataStore.edit { preferences -> preferences[KEY_UUID] = uuid }
    }

    override suspend fun getAccessToken(): String = dataStore.data
        .catch { exception ->
            when (exception) {
                is IOException -> emit(emptyPreferences())
                else -> throw exception
            }
        }.first()[KEY_ACCESS_TOKEN] ?: ""

    override suspend fun getRefreshToken(): String = dataStore.data
        .catch { exception ->
            when (exception) {
                is IOException -> emit(emptyPreferences())
                else -> throw exception
            }
        }.first()[KEY_REFRESH_TOKEN] ?: ""

    override suspend fun getUUID(): Long = dataStore.data
        .catch { exception ->
            when (exception) {
                is IOException -> emit(emptyPreferences())
                else -> throw exception
            }
        }.first()[KEY_UUID] ?: 0L

    override suspend fun clearAccessToken() {
        analyticsHelper.d { "clearAccessToken - entry" }
        dataStore.edit { it.remove(KEY_ACCESS_TOKEN) }
    }

    override suspend fun clearRefreshToken() {
        analyticsHelper.d { "clearRefreshToken - entry" }
        dataStore.edit { it.remove(KEY_REFRESH_TOKEN) }
    }

    override suspend fun clearAllToken() {
        analyticsHelper.d { "clearAllToken - entry" }
        dataStore.edit { it.clear() }
    }

    companion object {
        private val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val KEY_UUID = longPreferencesKey("uuid")
    }
}
