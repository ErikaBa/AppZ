package com.example.veterinariaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veterinariaz.clases.AppCitas.Companion.preferencias
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLoginClick()
        if (revisarEstao()) {
            startActivity(Intent(this, Opciones::class.java))
            startActivity(intent)
        }
    }
    private fun btnLoginClick (){
        btnLogin.setOnClickListener{
           preferencias.guardarEstado(btnRecuerdame.isChecked())
           val intent = Intent(this,Opciones::class.java)
            startActivity(intent)
        }
    }
    private fun revisarEstao(): Boolean{
        return preferencias.devolverEstado()
    }
}