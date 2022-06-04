package com.example.veterinariaz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.veterinariaz.clases.Menu
import com.example.veterinariaz.clases.MenuAdapter
import kotlinx.android.synthetic.main.activity_menu.*

data class MenuVeterinaria(var nombre:String, var detalle:String )

class MenuActivity : AppCompatActivity() {
    var menuVeterinaria = arrayListOf(
        MenuVeterinaria(nombre = "Consulta normal", detalle = "En la clínica"),
        MenuVeterinaria(nombre = "Consulta domicilio", detalle = "Ir a domicilio"),
        MenuVeterinaria(nombre = "Revision periódica", detalle = "Consulta para revisión"),
    )

    private lateinit var database:AppDataBase
    private lateinit var menu:Menu
    private lateinit var menuLiveData:LiveData<Menu>
    private val EDITAR = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //crearLista()
        click()
        cargarListaMenus()
    }

    private  fun crearLista(){
        var adaptador = Adaptador(applicationContext, menuVeterinaria)
        lstMenu.adapter = adaptador
    }

    private class Adaptador (context: Context, datos:ArrayList<MenuVeterinaria>): BaseAdapter()
    {
        val datosMenu = datos
        val ctx = context

        private inner class ViewHolder ()
        {
            internal var nombre:TextView? = null
            internal var detalle:TextView? = null
            internal var flecha: ImageView? = null
        }

        override fun getCount(): Int {
            return datosMenu.size
        }

        override fun getItem(position: Int): Any {
            return datosMenu[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, row: View?, parent: ViewGroup?): View {
            var viewHolder : ViewHolder
            var rowView = row

            if (rowView == null)
            {
                viewHolder = ViewHolder()
                val inflater = LayoutInflater.from(ctx)
                rowView = inflater.inflate(R.layout.fila_personalizada, parent, false)

                viewHolder.nombre = rowView.findViewById(R.id.txtNombre) as TextView
                viewHolder.detalle = rowView.findViewById(R.id.txtDetalle) as TextView
                viewHolder.flecha = rowView.findViewById(R.id.imgFlecha) as ImageView
                rowView.tag = viewHolder
            }
            else{
                viewHolder = rowView.tag as ViewHolder
            }
            viewHolder.nombre!!.setText(datosMenu.get(position).nombre)
            viewHolder.detalle!!.setText(datosMenu.get(position).detalle)
            viewHolder.flecha!!.setImageResource(R.drawable.flechaderecha)

            return rowView!!
        }

    }

    private fun click(){
        btnNuevoMenu.setOnClickListener{
            val intent = Intent(applicationContext, NuevoMenu::class.java)
            startActivity(intent)
        }
    }
    private fun cargarListaMenus(){
        var listaMenus = emptyList<Menu>()
        val dataBase = AppDataBase.getDatabase(this)

        dataBase.menus().obtenerMenus().observe(this, Observer {
            listaMenus = it
            val adaptador = MenuAdapter(this,listaMenus)
            lstMenu.adapter = adaptador
        })
        lstMenu.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,DetalleMenu::class.java)
            intent.putExtra("id", listaMenus[position].idMenu)
            startActivity(intent)
        }
    }
}