package com.example.creativepantry

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegistroViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

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
    fun `contrasenacurta`() {
        viewModel.validarCampos("usuario", "correo@mail.com", "12", "12")
        assertEquals("Las contraseñas es demasiado corta", viewModel.errorMessage.value)
    }

    @Test
    fun `contrasenasdiferentes`() {
        viewModel.validarCampos("usuario", "correo@mail.com", "1234", "4321")
        assertEquals("Las contraseñas no coinciden", viewModel.errorMessage.value)
    }

    @Test
    fun `registrocorrecto`() {
        viewModel.validarCampos("usuario", "correo@mail.com", "1234", "1234")
        assertEquals(null, viewModel.errorMessage.value)
        assertTrue(viewModel.registroCorrecto.value == true)
    }

    @Test
    fun `emailvaicio`() {
        viewModel.validarCampos("usuario", "", "1234", "1234")
        assertEquals("El email no puede estar vacío", viewModel.errorMessage.value)
    }

}
