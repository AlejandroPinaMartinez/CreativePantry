package com.example.creativepantry

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.example.creativepantry.analytics.UsageAnalyticsStorage
import kotlinx.coroutines.*

class Grafic1 : AppCompatActivity() {

    private lateinit var usageAnalyticsStorage: UsageAnalyticsStorage
    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grafic1)

        usageAnalyticsStorage = UsageAnalyticsStorage(this)
        barChart = findViewById(R.id.barChart)
        pieChart = findViewById(R.id.pieChart)

        loadBarChart()
        loadPieChart()
    }

    private fun loadBarChart() {
        CoroutineScope(Dispatchers.IO).launch {
            val activities = listOf("Detall", "Perfil")
            val barEntries = mutableListOf<BarEntry>()
            val labels = mutableListOf<String>()

            activities.forEachIndexed { index, activity ->
                val count = usageAnalyticsStorage.getUsageCount(activity)
                barEntries.add(BarEntry(index.toFloat(), count.toFloat()))
                labels.add(activity)
            }

            withContext(Dispatchers.Main) {
                val barDataSet = BarDataSet(barEntries, "USO CREATIVEPANTRY")
                barDataSet.colors = listOf(Color.BLUE, Color.RED)

                barChart.data = BarData(barDataSet)
                barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                barChart.animateY(1500)
                barChart.invalidate()
            }
        }
    }

    private fun loadPieChart() {
        CoroutineScope(Dispatchers.IO).launch {
            val activities = listOf("Afegir", "Editar")
            val pieEntries = mutableListOf<PieEntry>()

            activities.forEach { activity ->
                val count = usageAnalyticsStorage.getUsageCount(activity)
                pieEntries.add(PieEntry(count.toFloat(), activity))
            }

            withContext(Dispatchers.Main) {
                val pieDataSet = PieDataSet(pieEntries, "USO  RECETAS-USUARIO")
                pieDataSet.colors = listOf(Color.GREEN, Color.YELLOW)
                val pieData = PieData(pieDataSet)

                pieChart.data = pieData
                pieChart.animateY(1000, Easing.EaseInOutQuad)
                pieChart.invalidate()
            }
        }
    }
}
