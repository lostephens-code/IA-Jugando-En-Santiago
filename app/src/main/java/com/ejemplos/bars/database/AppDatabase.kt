package com.ejemplos.bars.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun usuarioDao(): UsuarioDao
}

companion object{
    const val NOMBRE_BD = "usuario.db"

    fun crear(context: Context): AppDatabase=
        Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java,
            NOMBRE_BD).build()
}