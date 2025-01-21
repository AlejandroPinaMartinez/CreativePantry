package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.android.material.navigation.NavigationView

class AjustesPreferencias : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var  toolbar :Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ajustes_preferencias)

        val menu=findViewById<BottomNavigationView>(R.id.menu_down)
        menu.setOnItemSelectedListener {
            when (it.itemId){
                R.id.menu_stats -> {
                    val intent = Intent(this, PantallaInicio::class.java)
                    startActivity(intent)
                }
                R.id.menu_inici -> {
                    val intent = Intent(this, PantallaInicio::class.java)
                    startActivity(intent)
                }
                R.id.menu_perfil -> {
                    val intent = Intent(this, PantallaInicio::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        toolbar=findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = findViewById(R.id.main_drawerlayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView =findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this::navigationItemSelectedListener)
    }

    private fun navigationItemSelectedListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_perfil -> {
                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_guardados -> {
                Toast.makeText(this, "Guardados", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Filtrado::class.java)
                startActivity(intent)
            }
            R.id.menu_premium-> {
                Toast.makeText(this, "Resumen", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Filtrado::class.java)
                startActivity(intent)
            }
            R.id.menu_ajustes -> {
                Toast.makeText(this, "Mis Clases", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Filtrado::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        if (supportFragmentManager.fragments.size<=2){
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("onOptionsItemSelected",item.itemId.toString())
        return super.onOptionsItemSelected(item)
    }

}