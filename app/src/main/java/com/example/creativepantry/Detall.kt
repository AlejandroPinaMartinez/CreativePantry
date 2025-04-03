package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.creativepantry.analytics.UsageAnalyticsStorage
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class Detall : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar
    private lateinit var usageAnalyticsStorage: UsageAnalyticsStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detall)

        usageAnalyticsStorage = UsageAnalyticsStorage(applicationContext)

        lifecycleScope.launch {
            usageAnalyticsStorage.incrementUsageCount("Detall")
        }

        val tvTitulo: TextView = findViewById(R.id.tvRecetaTitulo)
        val tvPuntuacion: TextView = findViewById(R.id.tvRecetaPuntuacion)
        val tvTiempo: TextView = findViewById(R.id.tvRecetaTiempo)
        val ivImagen: ImageView = findViewById(R.id.ivRecetaImagen)
        val tvIngredientes: TextView = findViewById(R.id.tvIngredientes)
        val tvPasos: TextView = findViewById(R.id.tvPasos)

        val titulo = intent.getStringExtra("titulo") ?: "Sin título"
        val puntuacion = intent.getFloatExtra("puntuacion", 0.0f)
        val tiempo = intent.getIntExtra("tiempo", 0)
        val imagen = intent.getStringExtra("imagen") ?: "plato1"
        val ingredientes = intent.getStringArrayListExtra("ingredientes") ?: arrayListOf()
        val pasos = intent.getStringArrayListExtra("pasos") ?: arrayListOf()

        tvTitulo.text = titulo
        tvPuntuacion.text = "Puntuación: $puntuacion★"
        tvTiempo.text = "Tiempo: $tiempo min"

        // Cargar imagen con Glide
        if (imagen.startsWith("http") || imagen.startsWith("content")) {
            Glide.with(this)
                .load(imagen)
                .placeholder(R.drawable.plato1) // Imagen por defecto mientras carga
                .error(R.drawable.plato1) // Imagen en caso de error
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImagen)
        } else {
            val resourceId = resources.getIdentifier(imagen, "drawable", packageName)
            Glide.with(this)
                .load(resourceId.takeIf { it != 0 } ?: R.drawable.plato1)
                .placeholder(R.drawable.plato1)
                .error(R.drawable.plato1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImagen)
        }

        tvIngredientes.text = ingredientes.joinToString("\n• ", "Ingredientes:\n• ")
        tvPasos.text = pasos.joinToString("\n\n", "Pasos:\n")

        val menu = findViewById<BottomNavigationView>(R.id.menubottom)
        menu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_stats -> startActivity(Intent(this, PantallaInicio::class.java))
                R.id.menu_inici -> startActivity(Intent(this, BuscarIngredientes::class.java))
                R.id.menu_perfil -> startActivity(Intent(this, Perfil::class.java))
            }
            true
        }

        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = findViewById(R.id.main_drawerlayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this::navigationItemSelectedListener)
    }

    private fun navigationItemSelectedListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_perfil -> {
                showToast("Perfil")
                startActivity(Intent(this, Perfil::class.java))
            }
            R.id.menu_guardados -> {
                showToast("Guardados")
                startActivity(Intent(this, Filtrado::class.java))
            }
            R.id.menu_premium -> {
                showToast("Premium")
                startActivity(Intent(this, Filtrado::class.java))
            }
            R.id.menu_ajustes -> {
                showToast("Ajustes")
                startActivity(Intent(this, AjustesPreferencias::class.java))
            }
            R.id.menu_tutorial -> {
                showToast("Tutorial")
                startActivity(Intent(this, Splash_Tutorial_1::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        if (supportFragmentManager.fragments.size <= 2) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("onOptionsItemSelected", item.itemId.toString())
        return super.onOptionsItemSelected(item)
    }
}
