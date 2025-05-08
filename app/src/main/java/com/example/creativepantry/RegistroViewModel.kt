package com.example.creativepantry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistroViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String?>()
    val registroCorrecto = MutableLiveData<Boolean>()

    fun validarCampos(
        nombreUsuario: String,
        email: String,
        password: String,
        repetirPassword: String
    ) {
        when {
            nombreUsuario.isBlank() -> errorMessage.value = "El nombre de usuario no puede estar vacío"
            email.isBlank() -> errorMessage.value = "El email no puede estar vacío"
            !Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matches(email) -> errorMessage.value = "Correo electrónico inválido"
            password != repetirPassword -> errorMessage.value = "Las contraseñas no coinciden"
            password.length < 4 -> errorMessage.value = "Las contraseñas es demasiado corta"
            password.length > 100 -> errorMessage.value = "Las contraseñas es demasiado larga"
            else -> {
                errorMessage.value = null
                registroCorrecto.value = true
            }
        }
    }
}

