package com.example.veterinariaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.veterinariaz.clases.AppCitas.Companion.preferencias
import kotlinx.android.synthetic.main.activity_opciones.*

class Opciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
        clickBotones()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_superior, menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.opCerrarSesion -> {
                //preferencias.almacenamiento.edit().clear()
                preferencias.guardarEstado(false)
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun clickBotones (){
        btnMenu.setOnClickListener{
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
        btnCitas.setOnClickListener{
            val intent = Intent(this,Citas::class.java)
            startActivity(intent)
        }
    }
}