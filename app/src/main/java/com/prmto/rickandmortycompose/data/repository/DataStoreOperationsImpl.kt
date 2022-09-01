package com.prmto.rickandmortycompose.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.prmto.rickandmortycompose.domain.model.enums.ListType
import com.prmto.rickandmortycompose.domain.repository.DataStoreOperations
import com.prmto.rickandmortycompose.util.Constant.PREFERENCES_KEY
import com.prmto.rickandmortycompose.util.Constant.PREFERENCES_NAME
import com.prmto.rickandmortycompose.util.Constant.VERTICAL_GRID_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    private object PreferencesKey {
        val listType = stringPreferencesKey(PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveListType(listType: ListType) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.listType] = listType.listName
        }
    }

    override fun readListType(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                val listType = preferences[PreferencesKey.listType] ?: VERTICAL_GRID_NAME
                listType
            }
    }
}