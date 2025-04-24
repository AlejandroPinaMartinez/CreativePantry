package com.example.creativepantry

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RegistroViewModelTest {

    private lateinit var viewModel: RegistroViewModel

    @Before
    fun setUp() {
        viewModel = RegistroViewModel()
    }

    @Test
    fun `nombredeusuariovacio`() {
        viewModel.validarCampos("", "test@email.com", "1234", "1234")
        assertEquals("El nombre de usuario no puede estar vacío", viewModel.errorMessage.value)
    }

    @Test
    fun `correoincorrecto`() {
        viewModel.validarCampos("usuario", "correo", "1234", "1234")
        assertEquals("Correo electrónico inválido", viewModel.errorMessage.value)
    }

    @Test
    fun `contraseñasdiferentes`() {
        viewModel.validarCampos("usuario", "correo@mail.com", "1234", "4321")
        assertEquals("Las contraseñas no coinciden", viewModel.errorMessage.value)
    }

    @Test
    fun `registrocorrecto`() {
        viewModel.validarCampos("usuario", "correo@mail.com", "1234", "1234")
        assertTrue(viewModel.registroCorrecto.value == true)
    }
}
