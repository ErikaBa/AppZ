package com.example.veterinariaz.clases

import android.content.Context

class Preferencias(val context: Context) {

    val SHARED_APP = "MyApp"
    val SHARED_ESTADO = "estado"

    val almacenamiento = context.getSharedPreferences(SHARED_APP, Context.MODE_PRIVATE)

    fun guardarEstado(estado:Boolean){
        almacenamiento.edit().putBoolean(SHARED_ESTADO, estado).apply()
    }
    fun devolverEstado():Boolean{
        return almacenamiento.getBoolean(SHARED_ESTADO, false)
    }
}