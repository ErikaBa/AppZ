package com.example.veterinariaz.clases

import android.app.Application

class AppCitas:Application() {
    companion object {
        lateinit var preferencias: Preferencias
    }
    override fun onCreate() {
        super.onCreate()
        preferencias= Preferencias(applicationContext)
    }
}