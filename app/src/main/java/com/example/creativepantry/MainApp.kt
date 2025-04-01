package com.example.creativepantry

import android.app.Application
import android.util.Log
import com.example.creativepantry.utils.StatisticsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainApp : Application() {

    companion object {
        lateinit var statisticsManager: StatisticsManager
        var recipeClickStats: MutableMap<Int, Int> = mutableMapOf()
    }

    override fun onCreate() {
        super.onCreate()
        statisticsManager = StatisticsManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            recipeClickStats = statisticsManager.getClickStats().toMutableMap()
            Log.i("App_onCreate", "Clics cargados desde DataStore: $recipeClickStats")
        }
    }

    fun registerClick(recipeId: Int?) {
        CoroutineScope(Dispatchers.IO).launch {
            statisticsManager.registerClick(recipeId)
            recipeClickStats[recipeId as Int] = (recipeClickStats[recipeId] ?: 0) + 1
            Log.i("registerClick", "Click registrado para receta $recipeId")
        }
    }
}
