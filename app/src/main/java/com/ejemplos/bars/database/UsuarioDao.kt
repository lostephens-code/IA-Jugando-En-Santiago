package com.ejemplos.bars.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarSiNoExisten(usuario: List<Usuario>)

    @Query("SELECT * FROM usuarios ORDER BY id ASC")
    fun obtenerTodos(): List<Usuario>
}