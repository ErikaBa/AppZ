package com.example.veterinariaz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.veterinariaz.clases.Menu
import com.example.veterinariaz.interfaces.MenuDAO

@Database(entities = [Menu::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun menus():MenuDAO

    companion object{
        @Volatile
        private var INSTANCIA:AppDataBase? = null

        fun getDatabase(contexto: Context):AppDataBase{
            val tempInstancia = INSTANCIA
            if(tempInstancia !=null){
                return tempInstancia
            }
            synchronized(this){
                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    AppDataBase::class.java,
                    "app_citas"
                ).build()

                INSTANCIA = instancia

                return instancia
            }
        }
    }
}
