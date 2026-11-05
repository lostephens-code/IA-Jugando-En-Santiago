package com.ejemplos.bars.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplos.bars.database.Usuario
import com.ejemplos.bars.database.UsuarioDao
import kotlinx.coroutines.launch

class AuthViewModel(private val userDao: UsuarioDao) : ViewModel() {

    // Estado del mensaje de respuesta/error
    var authMessage by mutableStateOf("")
        private set

    // Estado de la sesión actual
    var loggedInUser by mutableStateOf<Usuario?>(null)
        private set

    fun register(username: String, email: String, pass: String, onSuccess: () -> Unit) {
        if (username.isBlank() || email.isBlank() || pass.isBlank()) {
            authMessage = "Por favor completa todos los campos"
            return
        }

        viewModelScope.launch {
            val existingUser = userDao.getUserByEmail(email)
            if (existingUser != null) {
                authMessage = "El correo ya está registrado"
            } else {
                userDao.insertUser(Usuario(nombre = username, correo = email, contrasena = pass))
                authMessage = "¡Registro exitoso! Ya puedes iniciar sesión."
                onSuccess()
            }
        }
    }

    fun login(email: String, pass: String, onSuccess: () -> Unit) {
        if (email.isBlank() || pass.isBlank()) {
            authMessage = "Ingresa correo y contraseña"
            return
        }

        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            if (user != null && user.contrasena == pass) {
                loggedInUser = user
                authMessage = "Bienvenido, ${user.nombre}"
                onSuccess()
            } else {
                authMessage = "Credenciales incorrectas"
            }
        }
    }

    fun clearMessage() {
        authMessage = ""
    }
}