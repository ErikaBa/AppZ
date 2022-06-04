package com.example.veterinariaz.clases

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "menu")
class Menu(
    val nombre:String,
    val detalle:String,
    val costo:Double,
    val imagen:Int,
    @PrimaryKey(autoGenerate = true)
    var idMenu:Int = 0) :Serializable

