package com.example.creativepantry.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("recipe_clicks_prefs")

class StatisticsManager(private val context: Context) {

    companion object {
        val RECIPE_CLICKS_KEY = stringPreferencesKey("recipe_clicks")
    }

    suspend fun registerClick(recipeId: Int?) {
        context.dataStore.edit { preferences ->
            val currentClicks = preferences[RECIPE_CLICKS_KEY]?.let { deserialize(it) } ?: mutableMapOf()
            val newCount = (currentClicks[recipeId] ?: 0) + 1
            currentClicks[recipeId] = newCount
            preferences[RECIPE_CLICKS_KEY] = serialize(currentClicks)
        }
    }

    suspend fun getClickStats(): Map<Int, Int> {
        val preferences = context.dataStore.data.first()
        return preferences[RECIPE_CLICKS_KEY]?.let { deserialize(it) } ?: emptyMap()
    }

    suspend fun resetClicks() {
        context.dataStore.edit { preferences ->
            preferences.remove(RECIPE_CLICKS_KEY)
        }
    }

    private fun serialize(stats: Map<Int, Int>): String {
        return stats.entries.joinToString(";") { "${it.key}:${it.value}" }
    }

    private fun deserialize(data: String): MutableMap<Int, Int> {
        return data.split(";").mapNotNull {
            val parts = it.split(":")
            if (parts.size == 2) parts[0].toIntOrNull()?.let { id -> id to parts[1].toInt() } else null
        }.toMap().toMutableMap()
    }
}
