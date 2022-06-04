package com.example.veterinariaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_detalle_menu.*

class DetalleMenu : AppCompatActivity() {

    private lateinit var database:AppDataBase
    private lateinit var menu:com.example.veterinariaz.clases.Menu
    private lateinit var menuLiveData:LiveData<com.example.veterinariaz.clases.Menu>
    private val EDITAR = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_menu)
        cargarMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.acciones_menu, menu)
        return true
    }
    private fun cargarMenu(){
        database= AppDataBase.getDatabase(this)
        val idMenu = intent.getIntExtra("id",0)

        menuLiveData=database.menus().obtenerMenu(idMenu)
        menuLiveData.observe(this, Observer {
            menu = it

            txtDetalleMenuNombre.setText(menu.nombre)
            txtDetalleMenuDetalle.setText(menu.detalle)
            txtDetalleMenuCosto.setText("$  ${menu.costo}")

            val uriImagen = ControladorImagen.obtenerUriImagen(this,idMenu.toLong())
            imgDetalleMenu.setImageURI(uriImagen)
        })
    }
}