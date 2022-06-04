package com.example.veterinariaz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File
import java.net.URI

object ControladorImagen {
        fun seleccionarFotoGaleria(activity: Activity, codigo: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, codigo)
    }

        fun guardarImagen(contexto: Context, id: Long, uri: Uri) {
        val file = File(contexto.filesDir, id.toString())
        val bytes = contexto.contentResolver.openInputStream(uri)?.readBytes()!!
        file.writeBytes(bytes)
    }

        fun obtenerUriImagen(contexto: Context, id: Long): Uri {
        val file = File(contexto.filesDir, id.toString())
        return if (file.exists()) {
            Uri.fromFile(file)
        } else {
            Uri.parse("android.resource://dev.android.appcitas/drawable/placeholder")
        }
    }
        fun eliminarImagen(contexto: Context, id:Long) {
            val file = File(contexto.filesDir, id.toString())
            file.delete()
    }
}