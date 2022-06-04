package com.example.veterinariaz

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import kotlinx.android.synthetic.main.activity_nuevo_menu.*
import kotlinx.android.synthetic.main.fila_personalizada.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoMenu : AppCompatActivity() {
    private val SELECCIONAR = 1
    private var uriImagen:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_menu)
        cargar_imagen()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == SELECCIONAR && resultCode == Activity.RESULT_OK -> {
                uriImagen = data!!.data
                imgMenuNuevo.setImageURI(uriImagen)

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.aacion_guardar_menu, menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.opGuardarMenu -> {
                registrarMenu()
                true
                }
            }
            return super.onOptionsItemSelected(item)
        }

    private fun cargar_imagen(){
        imgMenuNuevo.setOnClickListener{
            ControladorImagen.seleccionarFotoGaleria(this, SELECCIONAR)
            }
        }
        private fun registrarMenu(){
            val database = AppDataBase.getDatabase(this)

            val nombre = txtMenuNombre.text.toString()
            val detalle = txtDetalles.text.toString()
            val costo = txtMenuPrecio.text.toString()

            val menu = com.example.veterinariaz.clases.Menu(nombre, detalle, costo.toDouble(), R.drawable.placeholder)
            CoroutineScope(Dispatchers.IO).launch{
                val id = database.menus().insertMenu(menu)[0]
                uriImagen?.let {
                    ControladorImagen.guardarImagen(this@NuevoMenu, id, it)
                }
                this@NuevoMenu.finish()
            }
    }
}