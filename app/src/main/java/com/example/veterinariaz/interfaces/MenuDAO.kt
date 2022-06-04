package com.example.veterinariaz.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.veterinariaz.clases.Menu

@Dao
interface MenuDAO {

    @Query("SELECT * FROM menu")
    fun obtenerMenus():LiveData<List<Menu>>

    @Query("SELECT * FROM menu WHERE idMenu = :id")
    fun obtenerMenu(id:Int):LiveData<Menu>

    @Insert
    fun insertMenu(vararg menu:Menu):List<Long>

    @Update
    fun actualizarMenu(menu: Menu)

    @Delete
    fun eliminarMenu(menu: Menu)

}