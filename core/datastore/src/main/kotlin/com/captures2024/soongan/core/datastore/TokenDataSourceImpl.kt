package com.captures2024.soongan.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenDataSourceImpl
@Inject
constructor(
    private val dataStore: DataStore<Preferences>,
) : TokenDataSource {

    override suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { preferences -> preferences[KEY_ACCESS_TOKEN] = accessToken }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { preferences -> preferences[KEY_REFRESH_TOKEN] = refreshToken }
    }

    override suspend fun setUUID(uuid: Long) {
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
        dataStore.edit { it.remove(KEY_ACCESS_TOKEN) }
    }

    override suspend fun clearRefreshToken() {
        dataStore.edit { it.remove(KEY_REFRESH_TOKEN) }
    }

    override suspend fun clearAllToken() {
        dataStore.edit { it.clear() }
    }

    companion object {
        private val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val KEY_UUID = longPreferencesKey("uuid")
    }
}