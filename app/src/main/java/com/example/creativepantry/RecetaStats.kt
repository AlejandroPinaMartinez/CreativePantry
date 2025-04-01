package com.example.creativepantry

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.creativepantry.databinding.ActivityStatsBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecetaStats : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Estadísticas de Recetas"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Cargar los datos desde DataStore
        loadStatistics()
    }

    private fun loadStatistics() {
        CoroutineScope(Dispatchers.IO).launch {
            val stats = (application as MainApp).statisticsManager.getClickStats()

            // Convertir los datos a formato de gráficos
            val barEntries = mutableListOf<BarEntry>()
            val pieEntries = mutableListOf<PieEntry>()
            var totalClicks = 0

            stats.entries.forEachIndexed { index, entry ->
                val recetaId = entry.key
                val numClicks = entry.value
                totalClicks += numClicks

                // Agregar datos al gráfico de barras
                barEntries.add(BarEntry(index.toFloat(), numClicks.toFloat()))

                // Agregar datos al gráfico de pastel
                pieEntries.add(PieEntry(numClicks.toFloat(), "Receta $recetaId"))
            }

            runOnUiThread {
                updateBarChart(barEntries)
                updatePieChart(pieEntries, totalClicks)
            }
        }
    }

    private fun updateBarChart(entries: List<BarEntry>) {
        val barDataSet = BarDataSet(entries, "Clicks por Receta").apply {
            colors = listOf(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA)
            valueTextColor = Color.BLACK
            valueTextSize = 14f
        }

        binding.barChart.apply {
            data = BarData(barDataSet)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            axisLeft.setDrawGridLines(false)
            axisRight.isEnabled = false
            description.text = "Veces que se ha visto cada receta"
            animateY(1000)
            invalidate()
        }
    }

    private fun updatePieChart(entries: List<PieEntry>, total: Int) {
        val pieDataSet = PieDataSet(entries, "Distribución de Visualizaciones").apply {
            colors = listOf(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA)
            valueTextColor = Color.BLACK
            valueTextSize = 14f
        }

        binding.pieChart.apply {
            data = PieData(pieDataSet)
            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 40f
            setHoleColor(Color.WHITE)
            animateY(1000)
            invalidate()
        }

        binding.txtTotalViews.text = "Total de Visualizaciones: $total"
    }
}
