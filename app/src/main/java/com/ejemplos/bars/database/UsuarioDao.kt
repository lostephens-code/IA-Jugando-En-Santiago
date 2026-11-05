package com.ejemplos.bars.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: Usuario): Long

    @Query("SELECT * FROM usuarios ORDER BY id ASC")
    fun obtenerTodos(): List<Usuario>

    @Query("SELECT * FROM usuarios WHERE correo = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): Usuario?
}